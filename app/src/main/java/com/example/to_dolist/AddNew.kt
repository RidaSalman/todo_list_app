package com.example.to_dolist
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.example.to_dolist.Database.Task
import com.example.to_dolist.Database.TaskDatabase
import com.example.to_dolist.Database.myExt.showToast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.util.*

class AddNew : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)

        val taskId = intent.getLongExtra("taskId", -1)
        val addTaskButton: Button = findViewById(R.id.button2)


        if (taskId != -1L) {
            addTaskButton.text = "UPDATE"
            // Fetch the task details from the database using the taskId
            lifecycleScope.launch {
                val task = TaskDatabase.getInstance(applicationContext).taskDao().getTaskById(taskId)

                // Populate the UI fields with the fetched task details
                task?.let {
                    findViewById<EditText>(R.id.title).setText(it.title)
                    findViewById<EditText>(R.id.category).setText(it.category)
                    findViewById<EditText>(R.id.description).setText(it.description)
                    findViewById<EditText>(R.id.date).setText(it.date)
                    findViewById<EditText>(R.id.status).setText(it.status)
                }
            }
        } else {
            // TaskId is not provided, it's a new task addition
            // Set the button text to "Add Task"
            addTaskButton.text = "ADD TASK"
        }

        addTaskButton.setOnClickListener {
            saveTaskToDatabase()
        }

        val items = listOf("Work", "Shopping", "Fun", "Category")
        val autoComplete: AutoCompleteTextView = findViewById(R.id.category)
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        autoComplete.setAdapter(adapter)
        autoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
                Toast.makeText(this, "Item: $itemSelected", Toast.LENGTH_SHORT).show()
            }

        val editText: TextInputEditText = findViewById(R.id.date)
        val calendar = Calendar.getInstance()

        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)
        editText.setText("$initialYear-${initialMonth + 1}-$initialDay")

        // Add click listener to the calendar drawable
        val calendarDrawableClickListener = {
            // Open the calendar dialog
            showCalendarDialog(editText)
        }
        val compoundDrawablePadding = resources.getDimensionPixelOffset(R.dimen.calendar_drawable_padding)
        editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_calendar_month_24, 0, 0, 0)
        editText.compoundDrawablePadding = compoundDrawablePadding
        editText.setOnClickListener { calendarDrawableClickListener.invoke() }
        editText.setOnTouchListener { _, event ->
            val drawableStart = 0
            if (event.action == MotionEvent.ACTION_UP && event.rawX <= editText.compoundDrawables[drawableStart].bounds.width() + compoundDrawablePadding) {
                calendarDrawableClickListener.invoke()
                true
            } else {
                false
            }
        }
    }

    private fun saveTaskToDatabase() {
        val title = findViewById<EditText>(R.id.title).text.toString()
        val category = findViewById<EditText>(R.id.category).text.toString()
        val description = findViewById<EditText>(R.id.description).text.toString()
        val date = findViewById<EditText>(R.id.date).text.toString()
        val status = findViewById<EditText>(R.id.status).text.toString()

        if (title.isNotEmpty() && category.isNotEmpty() && description.isNotEmpty() && date.isNotEmpty() && status.isNotEmpty()) {
            val taskId = intent.getLongExtra("taskId", -1)
            val newTask = Task(title = title, category = category, description = description, date = date, status = status)

            lifecycleScope.launch {
                if (taskId != -1L) {
                    // If taskId is not -1, it means we are updating an existing task
                    newTask.id = taskId // Set the taskId for the existing task
                    updateTaskInDatabase(newTask)
                } else {
                    // If taskId is -1, it means we are adding a new task
                    insertTaskToDatabase(newTask)
                }
                finish()
            }
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun insertTaskToDatabase(task: Task) {
        TaskDatabase.getInstance(applicationContext).taskDao().insert(task)

    }

    private suspend fun updateTaskInDatabase(task: Task) {
        TaskDatabase.getInstance(applicationContext).taskDao().update(task)
        showToast("Task updated")
        finish()
    }



    private fun showCalendarDialog(textInputEditText: TextInputEditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker?, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                // Handle the selected date
                val formattedMonth = (selectedMonth + 1).toString().padStart(2, '0')
                // Create a map to convert month number to month name
                val monthNames = mapOf(
                    "01" to "Jan", "02" to "Feb", "03" to "Mar", "04" to "Apr",
                    "05" to "May", "06" to "Jun", "07" to "Jul", "08" to "Aug",
                    "09" to "Sep", "10" to "Oct", "11" to "Nov", "12" to "Dec"
                )
                val selectedDate = String.format(
                    Locale.getDefault(),
                    "%02d %s, %d",
                    selectedDay,
                    monthNames[formattedMonth],
                    selectedYear
                )
                textInputEditText.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }
}
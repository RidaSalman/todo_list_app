package com.example.to_dolist

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.Adapters.CalenderAdapter
import com.example.to_dolist.Adapters.SubTaskAdapter
import com.example.to_dolist.Models.SubTask
import com.example.to_dolist.Database.TaskDatabase
import com.example.to_dolist.Dialogs.DialogAddTask
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.launch

class AddDetails : AppCompatActivity() {


    private lateinit var adapter: CalenderAdapter
    private lateinit var pieChart: PieChart
    private lateinit var recyclerViewSubTask: RecyclerView
    private lateinit var subtaskAdapter: SubTaskAdapter
    private val subTaskList = mutableListOf<SubTask>()
    private lateinit var button: ImageView
    private lateinit var percentage: TextView
    private val colorClassArray = intArrayOf(
        Color.GREEN,
        Color.GRAY,
    )
    private val colorsList = colorClassArray.toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_details)





        adapter = CalenderAdapter()
        pieChart = findViewById(R.id.pie_chart)
        button = findViewById(R.id.imageView_sub_task)
        percentage = findViewById(R.id.subTaskPercentage)
        pieChart.setUsePercentValues(true) // Show percentage values instead of raw values
        pieChart.description.isEnabled = false
        pieChart.setDrawEntryLabels(false)// Disable chart description



        pieChart.invalidate()

        val taskId = intent.getLongExtra("taskId", -1)
        if (taskId != -1L) {
            // Fetch the task details from the database using the taskId
            lifecycleScope.launch {
                val task =
                    TaskDatabase.getInstance(applicationContext).taskDao().getTaskById(taskId)

                // Update the UI with the task details
                task?.let {
                    findViewById<TextView>(R.id.textView3).text = it.date
                    findViewById<TextView>(R.id.txt_n).text = it.description
                    // Update other views with task details...
                }
            }
        }


        // Set up the RecyclerView for subtasks
        recyclerViewSubTask = findViewById(R.id.recyclerview_subtask)
        recyclerViewSubTask.layoutManager = LinearLayoutManager(this)
        subtaskAdapter = SubTaskAdapter(subTaskList){
            lifecycleScope.launch {
                TaskDatabase.getInstance(applicationContext).subtaskDao().update(it)
            }
        } // Pass the mutable list to the adapter
        recyclerViewSubTask.adapter = subtaskAdapter

        // Fetch and display subtasks when the activity is created
        fetchAndDisplaySubTasks()


        button.setOnClickListener {
            val dialog = DialogAddTask(this, true, onRightButtonClickListener = { dialog, subtaskTitle ->
                lifecycleScope.launch {
                    val subTask = SubTask(subTitle = subtaskTitle)
                    TaskDatabase.getInstance(applicationContext).subtaskDao().insert(subTask)





                }
                dialog.dismiss()

            }, onLeftButtonClickListener = { dialog ->
                dialog.dismiss()

            })

            dialog.show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchAndDisplaySubTasks() {
        // Fetch subtasks from the database using LiveData
        TaskDatabase.getInstance(applicationContext).subtaskDao().getAllSubTasks()
            .observe(this@AddDetails) { subTasks ->

                setPercentage(subTasks)
                // Update the mutable list with the new subtasks
                subTaskList.clear()
                subTaskList.addAll(subTasks)

                // Notify the adapter that the data has changed
                subtaskAdapter.notifyDataSetChanged()

                // Update the "10 task today" text view with the number of subtasks
                val subtasksCount = subTaskList.size
                val textViewTaskToday = findViewById<TextView>(R.id.textView)
                textViewTaskToday.text = "$subtasksCount task today"
            }
    }

    @SuppressLint("SetTextI18n")
    private fun setPercentage(subTask : List<SubTask>) {
        val completedCount = subTask.count { it.isComplete }
        val totalTasks = subTask.size
        val percentageCompleted = if (totalTasks > 0) {
            (completedCount * 100) / totalTasks
        } else {
            0
        }
        percentage.text = "$percentageCompleted%"

        setPieChart(subTask)
    }

    private fun dataValues1(subTaskList: List<SubTask>): ArrayList<PieEntry> {
        val completedCount = subTaskList.count { it.isComplete }
        val remainingCount = subTaskList.size - completedCount

        val dataVals = ArrayList<PieEntry>()
        dataVals.add(PieEntry(completedCount.toFloat(), "Completed"))
        dataVals.add(PieEntry(remainingCount.toFloat(), "Remaining"))


        return dataVals
    }


    private fun setPieChart(subTaskList: List<SubTask>) {
        val pieDataSet = PieDataSet(dataValues1(subTaskList), "")

        val pieData = PieData(pieDataSet)
        pieDataSet.colors = colorsList
        pieChart.data = pieData
        pieChart.invalidate()
    }


}
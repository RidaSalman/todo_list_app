package com.example.to_dolist

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import android.widget.PopupMenu
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class AddNew : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)

        val items = listOf("Work", "Shopping", "Fun", "Category")
        val autoComplete: AutoCompleteTextView = findViewById(R.id.auto_complete)
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        autoComplete.setAdapter(adapter)
        autoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
                Toast.makeText(this, "Item: $itemSelected", Toast.LENGTH_SHORT).show()
            }

        val editText: TextInputEditText = findViewById(R.id.time)
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

    private fun showCalendarDialog(textInputEditText: TextInputEditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker?, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                // Handle the selected date
                val selectedDate = String.format(
                    Locale.getDefault(),
                    "%d-%02d-%02d",
                    selectedYear,
                    selectedMonth + 1,
                    selectedDay
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
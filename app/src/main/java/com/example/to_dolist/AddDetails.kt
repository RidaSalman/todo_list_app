package com.example.to_dolist

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.Adapters.CalenderAdapter
import com.example.to_dolist.Database.TaskDatabase
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.launch

class AddDetails : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CalenderAdapter
    private lateinit var pieChart: PieChart
    val colorClassArray = intArrayOf(Color.LTGRAY, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GREEN, Color.MAGENTA, Color.RED)
    val colorsList = colorClassArray.toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_details)


        recyclerView = findViewById(R.id.recyclerview_calender)
        adapter = CalenderAdapter()
        pieChart = findViewById(R.id.pie_chart)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        val pieDataSet = PieDataSet(dataValues1(), "")
        pieDataSet.colors = colorsList

        val pieData = PieData(pieDataSet)
        pieData.setValueTextSize(12f) // Set the text size for the percentage values

        pieChart.data = pieData
        pieChart.setUsePercentValues(true) // Show percentage values instead of raw values
        pieChart.description.isEnabled = false
        pieChart.setDrawEntryLabels(false)// Disable chart description

        pieChart.invalidate()

        val taskId = intent.getLongExtra("taskId", -1)
        if (taskId != -1L) {
            // Fetch the task details from the database using the taskId
            lifecycleScope.launch {
                val task = TaskDatabase.getInstance(applicationContext).taskDao().getTaskById(taskId)

                // Update the UI with the task details
                task?.let {
                    findViewById<TextView>(R.id.textView3).text = it.date
                    findViewById<TextView>(R.id.txt_n).text = it.description
                    // Update other views with task details...
                }
            }
        }
    }

    private fun dataValues1(): ArrayList<PieEntry> {
        val dataVals = ArrayList<PieEntry>()

        dataVals.add(PieEntry(15f, "Sun"))
        dataVals.add(PieEntry(34f, "Mon"))
        dataVals.add(PieEntry(23f, "Tue"))
        dataVals.add(PieEntry(86f, "Wed"))
        dataVals.add(PieEntry(26f, "Thu"))
        dataVals.add(PieEntry(17f, "Fri"))
        dataVals.add(PieEntry(63f, "Sat"))

        return dataVals
    }
}

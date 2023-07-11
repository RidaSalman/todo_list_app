package com.example.to_dolist

import DataPreference
import DataPreference.Companion.Remember_UserName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.Adapters.FeaturedAdapter
import com.example.to_dolist.Helperclasses.FeaturedHelperClass
import kotlinx.coroutines.launch

class Dashboard : AppCompatActivity() {
    private lateinit var nameTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataPreference: DataPreference
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbaoad)

        dataPreference = DataPreference(this)


        nameTextView = findViewById(R.id.textView3)
        recyclerView = findViewById(R.id.recyclerview)

        initRecyclerView()


        if (intent.hasExtra("Name")) {
            name = intent.getStringExtra("Name").toString()
        } else {
            lifecycleScope.launch {
                name = dataPreference.getStringData(Remember_UserName)
            }
        }


        // Display the greeting message with the name
        val greetingMessage = "Hi, $name"
        nameTextView.text = greetingMessage
    }

    private fun initRecyclerView() {

        val data = ArrayList<FeaturedHelperClass>()
        /*val taskCount = ArrayList<TaskData>()
        val onGoingTask = ArrayList<OnGoingTask>()
        val complete = ArrayList<CompleteTask>()
        val cancelTask = ArrayList<CancelTask>()
        onGoingTask.add(OnGoingTask("one",""))
        complete.add(CompleteTask("one",""))
        complete.add(CompleteTask("two",""))
        cancelTask.add(CancelTask("one",""))
        cancelTask.add(CancelTask("two",""))

        taskCount.add(TaskData(onGoingTask,complete,cancelTask))*/

        data.add(FeaturedHelperClass("one", ContextCompat.getColor(this, R.color.colorWhite2)))
        data.add(FeaturedHelperClass("two", ContextCompat.getColor(this, R.color.colorLightPink)))
        data.add(FeaturedHelperClass("three", ContextCompat.getColor(this, R.color.colorLightgrey)))
        data.add(FeaturedHelperClass("four", ContextCompat.getColor(this, R.color.colorWhite2)))
        data.add(FeaturedHelperClass("five", ContextCompat.getColor(this, R.color.colorLightPink)))
        data.add(FeaturedHelperClass("five", ContextCompat.getColor(this, R.color.colorLightPink)))
        data.add(FeaturedHelperClass("five", ContextCompat.getColor(this, R.color.colorLightPink)))
        data.add(FeaturedHelperClass("five", ContextCompat.getColor(this, R.color.colorLightPink)))

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FeaturedAdapter(data)
    }


}

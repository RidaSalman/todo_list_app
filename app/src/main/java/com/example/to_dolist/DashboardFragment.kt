package com.example.to_dolist

import DataPreference
import DataPreference.Companion.Remember_UserName
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.Adapters.FeaturedAdapter
import com.example.to_dolist.Database.Task
import com.example.to_dolist.Database.TaskDatabase
import com.example.to_dolist.Database.myExt.showToast
import kotlinx.coroutines.launch


class DashboardFragment : Fragment(),  FeaturedAdapter.TaskDeleteListener {
    private lateinit var nameTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataPreference: DataPreference
    var name = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myView = inflater.inflate(R.layout.fragment_dashboard, container, false)

        nameTextView = myView.findViewById(R.id.textView3)
        recyclerView = myView.findViewById(R.id.recyclerview)
        dataPreference = DataPreference(requireContext())

        TaskDatabase.getInstance(requireContext().applicationContext).taskDao().getAllTasks().observe(viewLifecycleOwner){ todos ->
            initRecyclerView(todos)
        }


        if (requireActivity().intent.hasExtra("Name")) {
            name = requireActivity().intent.getStringExtra("Name").toString()
        } else {
            lifecycleScope.launch {
                name = dataPreference.getStringData(Remember_UserName)
            }
        }

        // Display the greeting message with the name
        val greetingMessage = "Hi, $name"
        nameTextView.text = greetingMessage

        return myView
    }

    override fun onDeleteTask(task: Task) {
        // Implement the logic to delete the task from the database here
        // For example, you can use the taskDao to delete the task
        lifecycleScope.launch {
            TaskDatabase.getInstance(requireContext().applicationContext).taskDao().delete(task)
            requireContext().showToast("Task Deleted")
        }
    }


    private fun initRecyclerView(data:List<Task>) {


        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = FeaturedAdapter(data, this)
    }


}
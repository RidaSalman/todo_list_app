package com.example.to_dolist

import DataPreference
import DataPreference.Companion.Remember_UserName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.Adapters.FeaturedAdapter
import com.example.to_dolist.Helperclasses.FeaturedHelperClass
import com.example.to_dolist.databinding.ActivityDashbaoadBinding
import com.example.to_dolist.databinding.ActivityUserInfoBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class Dashboard : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbaoad)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val container = findViewById<FrameLayout>(R.id.container)

        replaceFragment(DashboardFragment())

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(DashboardFragment())
                }
                R.id.clipboard -> {
                    replaceFragment(CreateTaskFragment())
                }
                R.id.menuboard -> {
                    replaceFragment(MenuFragment())
                }
                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }




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



}

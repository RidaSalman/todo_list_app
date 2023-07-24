package com.example.to_dolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

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

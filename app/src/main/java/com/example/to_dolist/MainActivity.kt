package com.example.to_dolist

import DataPreference
import DataPreference.Companion.Remember_UserName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var dataPreference: DataPreference



    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        dataPreference = DataPreference(this)


        lifecycleScope.launch {
            if (dataPreference.getStringData(Remember_UserName).isNotEmpty()){
                startDashboardActivity()
            }
        }


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.start_button)
        startButton.setOnClickListener {
            val intent = Intent(this@MainActivity, UserInfo::class.java)
            startActivity(intent)

        }
    }

    private fun startDashboardActivity() {


        val intent = Intent(this, Dashboard::class.java)
        startActivity(intent)
        finish()


    }
}
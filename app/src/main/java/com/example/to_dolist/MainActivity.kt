package com.example.to_dolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton= findViewById(R.id.start_button)
        startButton.setOnClickListener(View.OnClickListener{
            val intent = Intent(this@MainActivity, UserInfo::class.java)
            startActivity(intent)

        })
    }
}
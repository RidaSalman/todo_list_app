package com.example.to_dolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import androidx.appcompat.widget.SearchView;
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashbaoadAc : AppCompatActivity() {
    private lateinit var nameTextView: TextView
    private val USER_NAME_KEY = stringPreferencesKey("user_name")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbaoad)

        nameTextView = findViewById(R.id.textView3)

        val dataStore = applicationContext.dataStore

        lifecycleScope.launch {
            val name = withContext(Dispatchers.IO) {
                dataStore.data.first()[USER_NAME_KEY] ?: ""
            }

            // Display the greeting message with the name
            val greetingMessage = "Hi, $name"
            nameTextView.text = greetingMessage
        }
    }
}

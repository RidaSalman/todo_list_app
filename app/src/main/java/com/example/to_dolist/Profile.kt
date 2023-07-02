package com.example.to_dolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class Profile : AppCompatActivity() {
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var phoneTextView: TextView

    val USER_NAME_KEY = stringPreferencesKey("user_name")
    val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    val USER_PHONE_KEY = stringPreferencesKey("user_phone")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        nameTextView = findViewById(R.id.txt_n)
        emailTextView = findViewById(R.id.txt_e)
        phoneTextView = findViewById(R.id.txt_p)

        // Retrieve user information from Preferences DataStore
        val dataStore = applicationContext.dataStore
        lifecycleScope.launch {
            val preferences = dataStore.data.first()
            val name = preferences[USER_NAME_KEY]
            val email = preferences[USER_EMAIL_KEY]
            val phone = preferences[USER_PHONE_KEY]

            // Set the user information in TextViews
            nameTextView.text = "Name: $name"
            emailTextView.text = "Email: $email"
            phoneTextView.text = "Phone: $phone"
        }
    }
}
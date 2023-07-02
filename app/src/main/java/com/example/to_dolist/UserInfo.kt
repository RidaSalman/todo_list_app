package com.example.to_dolist

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserInfo : AppCompatActivity() {
    private lateinit var nameTextInput: TextInputLayout
    private lateinit var emailTextInput: TextInputLayout
    private lateinit var phoneTextInput: TextInputLayout
    private lateinit var registerButton: Button

    val USER_NAME_KEY = stringPreferencesKey("user_name")
    val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    val USER_PHONE_KEY = stringPreferencesKey("user_phone")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        nameTextInput = findViewById(R.id.reg_name)
        emailTextInput = findViewById(R.id.reg_email)
        phoneTextInput = findViewById(R.id.reg_phone)
        registerButton = findViewById(R.id.button)

        registerButton.setOnClickListener {
            // Get user input
            val name = nameTextInput.editText?.text.toString()
            val email = emailTextInput.editText?.text.toString()
            val phone = phoneTextInput.editText?.text.toString()

            // Validate user input
            if (name.isEmpty()) {
                nameTextInput.error = "Name is required"
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailTextInput.error = "Invalid email address"
                return@setOnClickListener
            }

            if (phone.isEmpty() || !phone.isValidPhoneNumber()) {
                phoneTextInput.error = "Invalid phone number"
                return@setOnClickListener
            }


            // Store user information in Preferences DataStore
            val dataStore = applicationContext.dataStore
            lifecycleScope.launch {
                dataStore.edit { preferences ->
                    preferences[USER_NAME_KEY] = name
                    preferences[USER_EMAIL_KEY] = email
                    preferences[USER_PHONE_KEY] = phone
                }

                showToast("Data saved")

                // Start the second activity
                val intent = Intent(this@UserInfo, DashbaoadAc::class.java)
                intent.putExtra("name", name)
                intent.putExtra("email", email)
                intent.putExtra("phone", phone)
                startActivity(intent)
            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

fun String.isValidPhoneNumber(): Boolean {
    // Customize the phone number validation based on your requirements
    // In this example, we check if the phone number is numeric and has a minimum length of 10 digits
    return this.matches(Regex("[0-9]+")) && this.length >= 11
}
package com.example.to_dolist

import DataPreference
import DataPreference.Companion.Remember_UserEmail
import DataPreference.Companion.Remember_UserName
import DataPreference.Companion.Remember_UserPhone
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.WindowManager
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class UserInfo : AppCompatActivity() {

    private lateinit var nameTextInput: TextInputEditText
    private lateinit var emailTextInput: TextInputEditText
    private lateinit var phoneTextInput: TextInputEditText
    private lateinit var registerButton: Button
    private lateinit var dataPreference: DataPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        dataPreference = DataPreference(this)

        nameTextInput = findViewById(R.id.reg_name)
        emailTextInput = findViewById(R.id.reg_email)
        phoneTextInput = findViewById(R.id.reg_phone)
        registerButton = findViewById(R.id.button)


        registerButton.setOnClickListener {
            // Get user input
            val name = nameTextInput.text.toString()
            val email = emailTextInput.text.toString()
            val phone = phoneTextInput.text.toString()

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

            startDashboardActivity()
        }
    }

    private fun startDashboardActivity() {
        lifecycleScope.launch{
            dataPreference.setStringData(Remember_UserName,nameTextInput.text.toString())
            dataPreference.setStringData(Remember_UserEmail,emailTextInput.text.toString())
            dataPreference.setStringData(Remember_UserPhone,phoneTextInput.text.toString())

        }
        val intent = Intent(this, Dashboard::class.java)
        intent.putExtra("Name",nameTextInput.text.toString())
        startActivity(intent)
    }

}

fun String.isValidPhoneNumber(): Boolean {
    // Customize the phone number validation based on your requirements
    // In this example, we check if the phone number is numeric and has a minimum length of 10 digits
    return this.matches(Regex("[0-9]+")) && this.length >= 11
}
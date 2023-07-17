package com.example.to_dolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class AddNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)

        val descTextInputLayout = findViewById<TextInputLayout>(R.id.desc)

        descTextInputLayout.setEndIconOnClickListener {
            showDropdownMenu(it)
        }
    }

    private fun showDropdownMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.category_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            val selectedCategory = item.title.toString()
            Toast.makeText(this, "Selected Category: $selectedCategory", Toast.LENGTH_SHORT).show()
            true
        }

        popupMenu.show()
    }
}
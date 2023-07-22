package com.example.to_dolist.Database.myExt

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment



fun Context.showToast(title: String){
    Toast.makeText(this, title,Toast.LENGTH_SHORT).show()
}
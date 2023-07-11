package com.example.to_dolist.Helperclasses

import android.graphics.drawable.Drawable


data class FeaturedHelperClass(
    val title: String,
    val color: Int = 0,
)

data class FeaturedHelperClass1(
    val image1: Drawable?,
    val title1: String,
    val description1: String,
    val color1:Int,
)
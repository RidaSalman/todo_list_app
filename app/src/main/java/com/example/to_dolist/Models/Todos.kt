package com.example.to_dolist.Database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val title: String,
    val category: String,
    val description: String,
    val date: String,
    val status: String

)
@Entity(tableName = "subtasks")
data class SubTask(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val subTitle: String,

)

package com.example.to_dolist.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.to_dolist.Models.SubTask
import com.example.to_dolist.Models.Task

@Dao
interface  TaskDao{
    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Long): Task?
}

@Dao
interface SubTaskDao{
    @Insert
    suspend fun insert(subTask: SubTask)

    @Update
    suspend fun update(subtask: SubTask)

    @Delete
    suspend fun delete(subtask: SubTask)

    @Query("SELECT * FROM subtasks ORDER BY id DESC")
    fun getAllSubTasks(): LiveData<List<SubTask>>
}
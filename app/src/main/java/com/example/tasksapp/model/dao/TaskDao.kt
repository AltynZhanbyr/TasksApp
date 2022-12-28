package com.example.tasksapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tasksapp.model.entity.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task:Task)

    @Update
    suspend fun updateTask(task:Task)

    @Query("select * from task_table where task_id==:id limit 1")
    fun getTask(id:Long): LiveData<Task>

    @Query("select * from task_table where task_id==:id")
    suspend fun getTaskCopy(id:Long): Task

    @Query("select * from task_table order by task_id desc")
    fun getAllTasks(): LiveData<List<Task>>?

//    @Query("select * from task_table")
//    suspend fun getAllTasksToDelete(): List<Task>?

    @Query("select * from task_table where task_done==:isDone order by task_id desc")
    fun getAllTasks(isDone:Boolean): LiveData<List<Task>>?



}
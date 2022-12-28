package com.example.tasksapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.tasksapp.model.dao.*;
import com.example.tasksapp.model.entity.*;
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class TasksViewModel (private val dao:TaskDao): ViewModel(){

    val allTasks = dao.getAllTasks()
    val allDoneTasks  = dao.getAllTasks(true)
    val allUndoneTasks  = dao.getAllTasks(false)



    var toAddTask = MutableLiveData(false)


    fun toAddingTask(){
        toAddTask.value=true
    }
}
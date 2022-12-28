package com.example.tasksapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.tasksapp.model.dao.*;
import com.example.tasksapp.model.entity.*;
import kotlinx.coroutines.launch

class TasksViewModel (private val dao:TaskDao): ViewModel(){

    val allTasks = dao.getAllTasks()
    val allDoneTasks  = dao.getAllTasks(true)
    val allUndoneTasks  = dao.getAllTasks(false)

    var task:LiveData<Task>?=null

    var taskDescription=""
    var taskName=""
    var taskID=""
    var taskIsDone = false

    var isTaskAdded = MutableLiveData(false)
    var isGoBack = MutableLiveData(false)
    var isTaskUpdated = MutableLiveData(false)
    var isTaskDeleted = MutableLiveData(false)
    var toAddTask = MutableLiveData(false)

    fun addTask(){
        val task = Task(null,taskName,taskDescription,taskIsDone)
        viewModelScope.launch {
            dao.insertTask(task)
            isTaskAdded.value = true
        }
    }

    fun getTask(id:Long){
        task = dao.getTask(id)
    }

    fun updateData(){
        val task = Task(taskID.toLong(),taskName,taskDescription,taskIsDone)
        viewModelScope.launch {
            dao.updateTask(task)
            isTaskUpdated.value = true
        }
    }

    fun deleteTask(){
        val task = Task(taskID.toLong(),taskName,taskDescription,taskIsDone)
        viewModelScope.launch {
            dao.deleteTask(task)
            isTaskDeleted.value = true
        }
    }

    fun toAddingTask(){
        toAddTask.value=true
    }

    fun goBack(){
        isGoBack.value = true
    }
}
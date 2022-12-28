package com.example.tasksapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasksapp.model.dao.TaskDao

class TasksViewModelFactory(private val id:Long,private val dao:TaskDao):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(TasksViewModel::class.java))
            TasksViewModel(dao) as T
        else if(modelClass.isAssignableFrom(EditTaskViewModel::class.java))
            EditTaskViewModel(id,dao) as T
        else
            throw IllegalArgumentException("Unknown ViewModel")
    }
}
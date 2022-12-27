package com.example.tasksapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasksapp.model.dao.TaskDao

class TasksViewModelFactory(private val dao:TaskDao):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TasksViewModel::class.java))
            return TasksViewModel(dao) as T
        else
            throw IllegalArgumentException("Unknown ViewModel")
    }
}
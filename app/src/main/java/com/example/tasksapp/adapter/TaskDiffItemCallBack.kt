package com.example.tasksapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.tasksapp.model.entity.Task

class TaskDiffItemCallBack:DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.Id == newItem.Id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem==newItem
    }
}
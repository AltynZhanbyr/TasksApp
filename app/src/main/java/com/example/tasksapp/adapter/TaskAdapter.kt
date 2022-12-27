package com.example.tasksapp.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksapp.R
import com.example.tasksapp.databinding.TaskItemBinding
import com.example.tasksapp.model.entity.Task

class TaskAdapter(private val onTaskClickListener: OnTaskClickListener)
    : ListAdapter<Task,TaskAdapter.TaskViewHolder>(TaskDiffItemCallBack()) {

    interface OnTaskClickListener{
        fun onTaskClick(task:Task)
    }

    class TaskViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val binding  = TaskItemBinding.bind(view)

        fun bind(task: Task,onTaskClickListener: OnTaskClickListener){
            binding.taskTitle.text = task.title
            binding.taskIsDone.isChecked = task.isDone
            itemView.setOnClickListener{
                onTaskClickListener.onTaskClick(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.task_item,parent,false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task,this.onTaskClickListener)
    }
}
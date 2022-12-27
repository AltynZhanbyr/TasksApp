package com.example.tasksapp.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tasksapp.R
import com.example.tasksapp.adapter.TaskAdapter
import com.example.tasksapp.databinding.FragmentDoneTasksBinding
import com.example.tasksapp.databinding.FragmentTaskBinding
import com.example.tasksapp.model.database.TaskDatabase
import com.example.tasksapp.model.entity.Task
import com.example.tasksapp.viewmodel.TasksViewModel
import com.example.tasksapp.viewmodel.TasksViewModelFactory

class DoneTasksFragment : Fragment() {
    private lateinit var binding: FragmentDoneTasksBinding
    private lateinit var adapter: TaskAdapter
    private lateinit var viewModel:TasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoneTasksBinding.inflate(inflater)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(activity).application
        val db = TaskDatabase.getDB(application);
        val dao = db.getTaskDao()

        val factory = TasksViewModelFactory(dao)
        viewModel = ViewModelProvider(this,factory)[TasksViewModel::class.java]

        binding.tasksViewModel = viewModel
        binding.lifecycleOwner=viewLifecycleOwner

        var onTaskClickListener = object: TaskAdapter.OnTaskClickListener{
            override fun onTaskClick(task: Task) {
                val action = DoneTasksFragmentDirections.actionDoneTasksFragmentToAddTaskFragment(task.Id!!)
                findNavController().navigate(action)
            }
        }
        adapter = TaskAdapter(onTaskClickListener)

        viewModel.toAddTask.observe(viewLifecycleOwner){
            if(it) {
                val action = DoneTasksFragmentDirections.actionDoneTasksFragmentToAddTaskFragment(-1)
                findNavController().navigate(action)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.allDoneTasks?.observe(viewLifecycleOwner){list->
            list?.let {
                adapter.submitList(list)
            }
        }
        binding.taskList.adapter=adapter
        adapter.notifyDataSetChanged()
    }

}
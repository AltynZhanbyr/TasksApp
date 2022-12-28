package com.example.tasksapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tasksapp.R
import com.example.tasksapp.adapter.TaskAdapter
import com.example.tasksapp.databinding.FragmentTaskBinding
import com.example.tasksapp.model.database.TaskDatabase
import com.example.tasksapp.model.entity.Task
import com.example.tasksapp.viewmodel.TasksViewModel
import com.example.tasksapp.viewmodel.TasksViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class TaskFragment : Fragment() {

    private lateinit var binding:FragmentTaskBinding
    private lateinit var adapter:TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(activity).application
        val db = TaskDatabase.getDB(application);
        val dao = db.getTaskDao()

        val factory = TasksViewModelFactory(-1,dao)
        val viewModel = ViewModelProvider(this,factory)[TasksViewModel::class.java]

        binding.tasksViewModel = viewModel
        binding.lifecycleOwner=viewLifecycleOwner

        var onTaskClickListener = object:TaskAdapter.OnTaskClickListener{
            override fun onTaskClick(task: Task) {
                val action = TaskFragmentDirections.actionTaskFragmentToAddTaskFragment(task.Id!!)
                findNavController().navigate(action)
            }
        }

        adapter = TaskAdapter(onTaskClickListener)

        lifecycleScope.launch{
            viewModel.allTasks?.observe(viewLifecycleOwner){list->
                list?.let {
                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()
                }
            }
        }
        viewModel.toAddTask.observe(viewLifecycleOwner){
            if(it) {
                val action = TaskFragmentDirections.actionTaskFragmentToAddTaskFragment(-1)
                findNavController().navigate(action)
            }
        }

        binding.taskList.adapter=adapter
        adapter.notifyDataSetChanged()

    }

}
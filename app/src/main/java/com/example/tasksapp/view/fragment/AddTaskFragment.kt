package com.example.tasksapp.view.fragment

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tasksapp.R
import com.example.tasksapp.databinding.FragmentAddTaskBinding
import com.example.tasksapp.model.database.TaskDatabase
import com.example.tasksapp.viewmodel.EditTaskViewModel
import com.example.tasksapp.viewmodel.TasksViewModel
import com.example.tasksapp.viewmodel.TasksViewModelFactory
import com.google.android.material.snackbar.Snackbar


class AddTaskFragment : Fragment() {

    private lateinit var binding:FragmentAddTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = AddTaskFragmentArgs.fromBundle(requireArguments()).taskID

        val application = requireNotNull(activity).application
        val db = TaskDatabase.getDB(application)
        val dao = db.getTaskDao()

        val factory = TasksViewModelFactory(id,dao)
        val viewModel = ViewModelProvider(this,factory)[EditTaskViewModel::class.java]

        binding.editTaskViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.isTaskAdded.observe(viewLifecycleOwner){isDone->
            goBack(isDone)
        }
        viewModel.isGoBack.observe(viewLifecycleOwner){isDone->
            goBack(isDone)
        }
        viewModel.isTaskUpdated.observe(viewLifecycleOwner){isDone->
            goBack(isDone)
        }
        viewModel.isTaskDeleted.observe(viewLifecycleOwner){isDone->
            goBack(isDone)
        }
        viewModel.isTitleEmpty.observe(viewLifecycleOwner){
            if(it)
                Snackbar.make(view,"Title cannot be empty!!!",Snackbar.LENGTH_SHORT).show()
        }

        if(id!=null){
            if(id!=(-1).toLong()){

                binding.saveTask.isEnabled=false
                binding.updateTask.isEnabled=true

                binding.delete.isEnabled=true
                binding.delete.isVisible = true
            }
        }

    }
    private fun goBack(bool:Boolean){
        if(bool){
            findNavController().navigate(R.id.action_addTaskFragment_to_taskFragment)
        }
    }
}
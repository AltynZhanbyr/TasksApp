package com.example.tasksapp.view.fragment

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tasksapp.R
import com.example.tasksapp.databinding.FragmentAddTaskBinding
import com.example.tasksapp.model.database.TaskDatabase
import com.example.tasksapp.viewmodel.TasksViewModel
import com.example.tasksapp.viewmodel.TasksViewModelFactory


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


        val application = requireNotNull(activity).application
        val db = TaskDatabase.getDB(application)
        val dao = db.getTaskDao()

        val factory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(this,factory)[TasksViewModel::class.java]

        binding.tasksViewModel = viewModel
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

        val id = AddTaskFragmentArgs.fromBundle(requireArguments()).taskID

        if(id!=null){
            if(id!=(-1).toLong()){
                binding.saveTask.isEnabled=false
                binding.updateTask.isEnabled=true
                viewModel.getTask(id)
                viewModel.task?.observe(viewLifecycleOwner){
                    binding.taskTitle.setText(it.title)
                    binding.taskDescription.setText(it.description)
                    binding.taskId.text = it.Id?.toString()
                    binding.taskIsDone.isChecked = it.isDone
                }
            }
        }

    }
    private fun goBack(bool:Boolean){
        if(bool){
            findNavController().navigate(R.id.action_addTaskFragment_to_taskFragment)
        }
    }
}
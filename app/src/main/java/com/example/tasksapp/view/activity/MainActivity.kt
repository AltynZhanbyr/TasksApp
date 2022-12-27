package com.example.tasksapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tasksapp.R
import com.example.tasksapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController:NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = findNavController(R.id.nav_host_fragment_main)

        setupWithNavController(binding.bottomNav,navController)

        navController.addOnDestinationChangedListener{controller,destination,_->
            when(destination.id){
                R.id.addTaskFragment->
                    binding.bottomNav.visibility=View.INVISIBLE
                else-> {
                    binding.bottomNav.visibility = View.VISIBLE
                }
            }
        }
    }
}
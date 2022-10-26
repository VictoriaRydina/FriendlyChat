package com.example.friendlychat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavInflater
import androidx.navigation.fragment.NavHostFragment
import com.example.friendlychat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.host_global) as NavHostFragment
    }
    private val navController: NavController by lazy { navHostFragment.navController }
    private val graphInflater: NavInflater by lazy { navController.navInflater }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        defineStartDestination()
    }

    private fun defineStartDestination() {
        val navGraph = graphInflater.inflate(R.navigation.navigation_global)
        val startDestination = R.id.mainMenuFragment

        navGraph.setStartDestination(startDestination)

        navController.graph = navGraph
    }
}
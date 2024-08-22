package com.github.gunin_igor75.jobsearchapp

import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.common.base.base.BaseActivity
import com.github.gunin_igor75.common.base.utils.Utils
import com.github.gunin_igor75.jobsearchapp.databinding.ActivityMainBinding
import com.github.gunin_igor75.network.dto.DataContainer

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(R.id.main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        val result = Utils.getData(
            context = applicationContext,
            path = "offers.json",
            clazz = DataContainer::class.java
        )
        Log.d(TAG, "!!!!!!!!$result")
    }

    private companion object{
        const val TAG = "MainActivity"
    }
}
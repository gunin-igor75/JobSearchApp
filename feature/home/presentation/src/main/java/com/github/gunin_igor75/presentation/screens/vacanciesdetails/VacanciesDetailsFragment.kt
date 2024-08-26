package com.github.gunin_igor75.presentation.screens.vacanciesdetails

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.common.base.base.BaseFragment
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.databinding.FragmentVacanciesDetailsBinding


class VacanciesDetailsFragment : BaseFragment(), MenuProvider {
    private val binding: FragmentVacanciesDetailsBinding by viewBinding(R.id.vacanciesDetailsFragment)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = binding.toolBar
        val navController = findNavController()
        val abbBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        toolbar.setupWithNavController(navController, abbBarConfiguration)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_vacancies_details, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.miEye -> {true}
            R.id.miShared -> {true}
            R.id.miFavorite -> {true}
            else -> false
        }
    }
}
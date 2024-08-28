package com.github.gunin_igor75.jobsearchapp

import android.os.Bundle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.common.base.base.BaseActivity
import com.github.gunin_igor75.common.base.utils.Constants.Companion.FAVORITE_ITEM_ID
import com.github.gunin_igor75.jobsearchapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(R.id.main)

    private val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        vm.getCountFavoritesVacancies()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            vm.countFavoriteVacancies.flowWithLifecycle(lifecycle).collect {
                updateBadge(it)
            }
        }
    }

    private fun updateBadge(count: Int) {
        val menu = binding.bottomNav.menu
        val child = menu.getItem(FAVORITE_ITEM_ID)
        val badge = binding.bottomNav.getOrCreateBadge(child.itemId)
        badge.isVisible = count != 0
        badge.number = count
    }
}
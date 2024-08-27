package com.github.gunin_igor75.presentation.screens.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.common.base.base.BaseFragment
import com.github.gunin_igor75.common.base.utils.Constants
import com.github.gunin_igor75.common.base.utils.Constants.Companion.COUNT_BEGIN_LIST_VACANCIES
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.adapter.UiOfferAdapter
import com.github.gunin_igor75.presentation.adapter.UiVacancyAdapter
import com.github.gunin_igor75.presentation.databinding.FragmentHomeBinding
import com.github.gunin_igor75.presentation.utils.decoration.MarginItemDecorationOffers
import com.github.gunin_igor75.presentation.utils.decoration.MarginItemDecorationVacancies
import com.github.gunin_igor75.presentation.utils.extension.followToLink
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment: BaseFragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding(R.id.homeFragment)

    private val uiOfferAdapter: UiOfferAdapter by lazy {
        UiOfferAdapter(
            followToLink = { followToLink(it) },
            raiseResume = {}
        )
    }

    private val uiVacancyAdapter: UiVacancyAdapter by lazy {
        UiVacancyAdapter(
            onClickItem = {
                val action =
                    HomeFragmentDirections.actionHomeFragment2ToVacanciesDetailsFragment2(it)
                findNavController().navigate(action)
            },
            onClickFavorite = {
                if (it.isFavorite) vm.removeFromFavorites(it.listItemId) else vm.addFavoriteVacancies(it)
            }
        )
    }

    private val vm: HomeViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViewOffers()
        setupRecyclerViewVacancies()
        observeViewModelOffers()
        observeViewModelVacancies()

    }

    private fun setupButtonCountVacancies(countVacancies: Int) {
        with(binding.buttonCountVacancies){
            if (countVacancies > COUNT_BEGIN_LIST_VACANCIES) {
                visibility = View.VISIBLE
                val remainder = countVacancies - COUNT_BEGIN_LIST_VACANCIES
                val result = resources.getQuantityString(R.plurals.more_vacancies, remainder, remainder)
                text = result
            } else {
                visibility = View.GONE
            }
        }
    }

    private fun setupRecyclerViewOffers() {
        val margin = resources.getDimensionPixelSize(com.github.gunin_igor75.common.R.dimen.dp_1x)
        val marginDecoration = MarginItemDecorationOffers(margin)
        with(binding.recyclerViewOffers) {
            adapter = uiOfferAdapter
            addItemDecoration(marginDecoration)
        }
    }

    private fun setupRecyclerViewVacancies() {
        val margin = resources.getDimensionPixelSize(com.github.gunin_igor75.common.R.dimen.dp_1x)
        val marginDecoration = MarginItemDecorationVacancies(margin)
        with(binding.recyclerViewVacancies) {
            adapter = uiVacancyAdapter
            addItemDecoration(marginDecoration)
        }
    }

    private fun observeViewModelOffers() {
        lifecycleScope.launch {
            vm.getOffers().flowWithLifecycle(viewLifecycleOwner.lifecycle).collect { offers ->
                if (offers != null) {
                    binding.recyclerViewOffers.visibility = View.VISIBLE
                    uiOfferAdapter.items = offers
                } else {
                    binding.recyclerViewOffers.visibility = View.GONE
                }
            }
        }
    }

    private fun observeViewModelVacancies() {
        vm.getVacanciesState()
        lifecycleScope.launch {
            vm.vacanciesState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect { state ->
                if (state.loading) {

                }
                if (state.data != null) {
                    val list = state.data.take(COUNT_BEGIN_LIST_VACANCIES)
                    uiVacancyAdapter.items = list
                    setupButtonCountVacancies(state.data.size)
                }
            }
        }
    }
}

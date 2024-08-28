package com.github.gunin_igor75.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.common.base.base.BaseFragment
import com.github.gunin_igor75.common.base.mappers.toFavoriteVacancyModel
import com.github.gunin_igor75.common.base.utils.MarginItemDecorationSimple
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.adapter.UiVacanciesFavoritesAdapter
import com.github.gunin_igor75.presentation.databinding.FragmentFavoritesBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {
    private val binding: FragmentFavoritesBinding by viewBinding(R.id.favorites)

    private val vm: FavoritesVacanciesViewModel by viewModel()

    private val uiVacancyFavoriteAdapter: UiVacanciesFavoritesAdapter by lazy {
        UiVacanciesFavoritesAdapter(
            onClickFavorite = {
                val favorite = it.toFavoriteVacancyModel()
                if (it.isFavorite) vm.removeFromFavorites(it.listItemId) else vm.addFavoriteVacancies(
                    favorite.copy(isFavorite = true)
                )
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeFavoritesVacancies()
    }

    private fun setupRecyclerView() {
        val margin = resources.getDimensionPixelSize(com.github.gunin_igor75.common.R.dimen.dp_1x)
        val marginDecoration = MarginItemDecorationSimple(margin)
        with(binding.recyclerView) {
            adapter = uiVacancyFavoriteAdapter
            addItemDecoration(marginDecoration)
        }
    }

    private fun observeFavoritesVacancies() {
        lifecycleScope.launch {
            vm.getFavoritesVacancies().flowWithLifecycle(viewLifecycleOwner.lifecycle).collect{
                uiVacancyFavoriteAdapter.items = it
                setupCountFavoriteVacancies(it.size)
            }
        }
    }

    private fun setupCountFavoriteVacancies(count: Int) {
        val text = resources.getQuantityString(
            com.github.gunin_igor75.common.R.plurals.count_vacancies,
            count,
            count
        )
        binding.textViewCountVacancies.text = text
    }
}
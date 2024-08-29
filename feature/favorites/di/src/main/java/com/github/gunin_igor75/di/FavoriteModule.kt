package com.github.gunin_igor75.di

import com.github.gunin_igor75.presentation.screens.FavoritesFragment
import com.github.gunin_igor75.presentation.screens.FavoritesVacanciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel<FavoritesVacanciesViewModel>{
        FavoritesVacanciesViewModel(
            getFavoritesVacanciesUseCase = get(),
            addFavoriteUseCase = get(),
            removeFromFavoritesUseCase = get()
        )
    }
    scope<FavoritesFragment> {
        viewModel<FavoritesVacanciesViewModel> {
            FavoritesVacanciesViewModel(
                getFavoritesVacanciesUseCase = get(),
                addFavoriteUseCase = get(),
                removeFromFavoritesUseCase = get()
            )
        }
    }
}
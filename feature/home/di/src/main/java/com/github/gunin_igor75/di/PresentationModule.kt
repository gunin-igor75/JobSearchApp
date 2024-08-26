package com.github.gunin_igor75.di

import com.github.gunin_igor75.presentation.screens.home.HomeViewModel
import com.github.gunin_igor75.presentation.screens.login.LoginViewModel
import com.github.gunin_igor75.presentation.screens.verification.VerificationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val presentationModule = module {
    viewModel<LoginViewModel>{
        LoginViewModel()
    }
    viewModel<VerificationViewModel> {
        VerificationViewModel()
    }
    viewModel<HomeViewModel>{
        HomeViewModel(
            getOffersUseCase = get(),
            getVacanciesStateUseCase = get(),
            addFavoriteUseCase = get(),
            removeFromFavoritesUseCase = get()
        )
    }
}
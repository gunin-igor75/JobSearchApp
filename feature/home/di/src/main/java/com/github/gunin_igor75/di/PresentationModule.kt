package com.github.gunin_igor75.di

import com.github.gunin_igor75.presentation.screens.home.HomeViewModel
import com.github.gunin_igor75.presentation.screens.login.LoginViewModel
import com.github.gunin_igor75.presentation.screens.vacancies.VacanciesViewModel
import com.github.gunin_igor75.presentation.screens.vacanciesdetails.VacanciesDetailsViewModel
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
    viewModel<VacanciesViewModel> {
        VacanciesViewModel(
            getVacanciesUseCase = get(),
            addFavoriteUseCase = get(),
            removeFromFavoritesUseCase = get()
        )
    }
    viewModel<VacanciesDetailsViewModel> {
        VacanciesDetailsViewModel(
            isFavoritesVacancyUseCase = get(),
            addFavoriteUseCase = get(),
            removeFromFavoritesUseCase = get()
        )
    }
}
package com.github.gunin_igor75.jobsearchapp.di

import com.github.gunin_igor75.jobsearchapp.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val mainModule = module {
    viewModel<MainViewModel>{
        MainViewModel(getCountFavoritesUseCase = get())
    }
}
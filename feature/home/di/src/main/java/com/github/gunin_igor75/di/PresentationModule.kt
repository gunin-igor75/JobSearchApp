package com.github.gunin_igor75.di

import com.github.gunin_igor75.presentation.screens.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val presentationModule = module {
    viewModel<LoginViewModel>{
        LoginViewModel()
    }
}
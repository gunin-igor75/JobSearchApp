package com.github.gunin_igor75.di

import com.github.gunin_igor75.domain.usecase.GetOffersUseCase
import com.github.gunin_igor75.domain.usecase.GetVacanciesStateUseCase
import com.github.gunin_igor75.domain.usecase.GetVacanciesUseCase
import org.koin.dsl.module

internal val domainModule = module {

    factory<GetOffersUseCase> {
        GetOffersUseCase(repository = get())
    }
    factory<GetVacanciesStateUseCase> {
        GetVacanciesStateUseCase(repository = get())
    }

    factory<GetVacanciesUseCase> {
        GetVacanciesUseCase(repository = get())
    }
}
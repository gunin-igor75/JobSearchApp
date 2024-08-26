package com.github.gunin_igor75.di

import com.github.gunin_igor75.domain.usecase.AddFavoriteUseCase
import com.github.gunin_igor75.domain.usecase.GetFavoritesVacanciesUseCase
import com.github.gunin_igor75.domain.usecase.GetOffersUseCase
import com.github.gunin_igor75.domain.usecase.IsFavoritesVacancyUseCase
import com.github.gunin_igor75.domain.usecase.RemoveFromFavoritesUseCase
import org.koin.dsl.module

internal val domainModule = module {
    factory<GetFavoritesVacanciesUseCase> {
        GetFavoritesVacanciesUseCase(repository = get())
    }
    factory<IsFavoritesVacancyUseCase> {
        IsFavoritesVacancyUseCase(repository = get())
    }
    factory<AddFavoriteUseCase> {
        AddFavoriteUseCase(repository = get())
    }
    factory<RemoveFromFavoritesUseCase> {
        RemoveFromFavoritesUseCase(repository = get())
    }
    factory<GetOffersUseCase> {
        GetOffersUseCase(repository = get())
    }
    factory<GetFavoritesVacanciesUseCase> {
        GetFavoritesVacanciesUseCase(repository = get())
    }
}
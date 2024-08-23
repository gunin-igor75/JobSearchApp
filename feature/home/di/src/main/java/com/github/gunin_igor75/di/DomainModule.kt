package com.github.gunin_igor75.di

import com.github.gunin_igor75.domain.usecase.AddFavorite
import com.github.gunin_igor75.domain.usecase.GetFavoritesVacancies
import com.github.gunin_igor75.domain.usecase.IsFavoritesVacancy
import com.github.gunin_igor75.domain.usecase.RemoveFromFavorites
import org.koin.dsl.module

internal val domainModule = module {
    factory<GetFavoritesVacancies> {
        GetFavoritesVacancies(repository = get())
    }
    factory<IsFavoritesVacancy> {
        IsFavoritesVacancy(repository = get())
    }
    factory<AddFavorite> {
        AddFavorite(repository = get())
    }
    factory<RemoveFromFavorites> {
        RemoveFromFavorites(repository = get())
    }
}
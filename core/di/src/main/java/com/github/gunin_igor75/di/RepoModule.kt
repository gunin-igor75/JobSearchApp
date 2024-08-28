package com.github.gunin_igor75.di

import com.github.gunin_igor75.repo.data.repository.FavoriteRepositoryImpl
import com.github.gunin_igor75.repo.domain.repository.FavoriteRepository
import com.github.gunin_igor75.repo.domain.usecase.AddFavoriteUseCase
import com.github.gunin_igor75.repo.domain.usecase.GetCountFavoritesUseCase
import com.github.gunin_igor75.repo.domain.usecase.GetFavoritesVacanciesUseCase
import com.github.gunin_igor75.repo.domain.usecase.IsFavoritesVacancyUseCase
import com.github.gunin_igor75.repo.domain.usecase.RemoveFromFavoritesUseCase
import org.koin.dsl.module

internal val repoModule = module {
    single<FavoriteRepository> {
        FavoriteRepositoryImpl(localDataSource = get())
    }

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

    factory<GetCountFavoritesUseCase> {
        GetCountFavoritesUseCase(repository = get())
    }
}
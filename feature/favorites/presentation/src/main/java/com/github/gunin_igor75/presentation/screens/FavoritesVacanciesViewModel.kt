package com.github.gunin_igor75.presentation.screens

import com.github.gunin_igor75.common.base.mappers.toUiVacanciesFavorites
import com.github.gunin_igor75.common.base.model.UiVacancyFavorite
import com.github.gunin_igor75.repo.base.BaseFavoriteViewModel
import com.github.gunin_igor75.repo.domain.usecase.AddFavoriteUseCase
import com.github.gunin_igor75.repo.domain.usecase.GetFavoritesVacanciesUseCase
import com.github.gunin_igor75.repo.domain.usecase.RemoveFromFavoritesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesVacanciesViewModel(
    private val getFavoritesVacanciesUseCase: GetFavoritesVacanciesUseCase,
    addFavoriteUseCase: AddFavoriteUseCase,
    removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
) : BaseFavoriteViewModel(addFavoriteUseCase, removeFromFavoritesUseCase) {

    fun getFavoritesVacancies(): Flow<List<UiVacancyFavorite>> = getFavoritesVacanciesUseCase()
        .map { it.toUiVacanciesFavorites() }
}
package com.github.gunin_igor75.presentation.screens.vacancies

import com.github.gunin_igor75.domain.usecase.GetVacanciesUseCase
import com.github.gunin_igor75.repo.base.BaseFavoriteViewModel
import com.github.gunin_igor75.common.base.mappers.toUiVacancies
import com.github.gunin_igor75.common.base.model.UiVacancy
import com.github.gunin_igor75.repo.domain.usecase.AddFavoriteUseCase
import com.github.gunin_igor75.repo.domain.usecase.RemoveFromFavoritesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VacanciesViewModel(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    addFavoriteUseCase: AddFavoriteUseCase,
    removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
) : BaseFavoriteViewModel(addFavoriteUseCase, removeFromFavoritesUseCase) {

    fun getVacancies(): Flow<List<UiVacancy>> = getVacanciesUseCase()
        .map { it.toUiVacancies() }

    fun getCountVacancies(): Flow<Int> = getVacanciesUseCase()
        .map { it.size }
}
package com.github.gunin_igor75.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.gunin_igor75.presentation.mappers.toFavoriteVacancyModel
import com.github.gunin_igor75.presentation.model.UiVacancy
import com.github.gunin_igor75.repo.domain.usecase.AddFavoriteUseCase
import com.github.gunin_igor75.repo.domain.usecase.RemoveFromFavoritesUseCase
import kotlinx.coroutines.launch

open class BaseFavoriteViewModel(
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
) : ViewModel() {

    fun addFavoriteVacancies(uiVacancy: UiVacancy) {
        viewModelScope.launch {
            addFavoriteUseCase(uiVacancy.toFavoriteVacancyModel())
        }
    }

    fun removeFromFavorites(vacanciesId: String) {
        viewModelScope.launch {
            removeFromFavoritesUseCase(vacanciesId)
        }
    }
}
package com.github.gunin_igor75.repo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.gunin_igor75.common.base.model.FavoriteVacancyModel
import com.github.gunin_igor75.common.base.model.UiVacancy
import com.github.gunin_igor75.repo.domain.usecase.AddFavoriteUseCase
import com.github.gunin_igor75.repo.domain.usecase.RemoveFromFavoritesUseCase
import com.github.gunin_igor75.repo.mappers.toFavoriteVacancyModel
import kotlinx.coroutines.launch

open class BaseFavoriteViewModel(
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
) : ViewModel() {

    fun addFavoriteVacancies(favoriteVacancyModel: FavoriteVacancyModel) {
        viewModelScope.launch {
            addFavoriteUseCase(favoriteVacancyModel)
        }
    }

    fun removeFromFavorites(vacanciesId: String) {
        viewModelScope.launch {
            removeFromFavoritesUseCase(vacanciesId)
        }
    }
}
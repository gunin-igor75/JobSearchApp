package com.github.gunin_igor75.presentation.screens.vacanciesdetails

import androidx.lifecycle.viewModelScope
import com.github.gunin_igor75.presentation.base.BaseFavoriteViewModel
import com.github.gunin_igor75.presentation.model.UiQuestion
import com.github.gunin_igor75.repo.domain.usecase.AddFavoriteUseCase
import com.github.gunin_igor75.repo.domain.usecase.IsFavoritesVacancyUseCase
import com.github.gunin_igor75.repo.domain.usecase.RemoveFromFavoritesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VacanciesDetailsViewModel(
    private val isFavoritesVacancyUseCase: IsFavoritesVacancyUseCase,
    addFavoriteUseCase: AddFavoriteUseCase,
    removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
) : BaseFavoriteViewModel(addFavoriteUseCase, removeFromFavoritesUseCase) {

    private val _isFavoriteState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFavoriteState = _isFavoriteState.asStateFlow()

    fun isFavorite(id: String) {
        viewModelScope.launch {
            isFavoritesVacancyUseCase(id).collect {
                _isFavoriteState.value = it
            }
        }
    }

    fun getUiQuestions(questions: List<String>): List<UiQuestion> {
        return questions.mapIndexed { index, question ->
            UiQuestion(
                listItemId = "$index",
                text = question
            )
        }
    }
}
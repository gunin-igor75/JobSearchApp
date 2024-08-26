package com.github.gunin_igor75.jobsearchapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.gunin_igor75.domain.usecase.GetFavoritesVacanciesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getFavoritesVacanciesUseCase: GetFavoritesVacanciesUseCase
): ViewModel() {

    private val _countFavoriteVacancies: MutableStateFlow<Int> = MutableStateFlow(0)
    val countFavoriteVacancies = _countFavoriteVacancies.asStateFlow()

    fun getCountFavoritesVacancies() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoritesVacanciesUseCase().collect{
                _countFavoriteVacancies.value = it.size
            }
        }
    }
}
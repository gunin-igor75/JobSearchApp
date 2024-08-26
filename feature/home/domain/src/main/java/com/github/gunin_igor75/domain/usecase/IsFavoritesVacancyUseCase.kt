package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.domain.repository.FavoriteRepository

class IsFavoritesVacancyUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke(vacanciesId: String) = repository.observeIsFavorites(vacanciesId)
}
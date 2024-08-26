package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.domain.repository.FavoriteRepository

class RemoveFromFavoritesUseCase(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(vacanciesId: String) = repository.removeFromFavorites(vacanciesId)
}
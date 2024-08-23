package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.domain.repository.FavoriteRepository

class RemoveFromFavorites(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(vacanciesId: String) = repository.removeFromFavorites(vacanciesId)
}
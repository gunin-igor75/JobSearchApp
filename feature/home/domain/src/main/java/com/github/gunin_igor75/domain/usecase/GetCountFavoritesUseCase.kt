package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.domain.repository.FavoriteRepository

class GetCountFavoritesUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke() = repository.getCountFavorites()
}
package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.domain.repository.FavoriteRepository

class GetFavoritesVacanciesUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke() = repository.getFavoritesVacancies()
}
package com.github.gunin_igor75.repo.domain.usecase

import com.github.gunin_igor75.repo.domain.repository.FavoriteRepository

class GetFavoritesVacanciesUseCase(
    private val repository: FavoriteRepository,
) {
    operator fun invoke() = repository.getFavoritesVacancies()
}
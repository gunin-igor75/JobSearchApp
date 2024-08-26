package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.domain.model.FavoriteVacancyModel
import com.github.gunin_igor75.domain.repository.FavoriteRepository

class AddFavoriteUseCase(
    private val repository: FavoriteRepository,
) {
    suspend operator fun invoke(favoriteVacancyModel: FavoriteVacancyModel) =
        repository.addFavorite(favoriteVacancyModel)
}
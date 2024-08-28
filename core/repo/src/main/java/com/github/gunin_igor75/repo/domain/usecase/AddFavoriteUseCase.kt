package com.github.gunin_igor75.repo.domain.usecase

import com.github.gunin_igor75.common.base.model.FavoriteVacancyModel
import com.github.gunin_igor75.repo.domain.repository.FavoriteRepository

class AddFavoriteUseCase(
    private val repository: FavoriteRepository,
) {
    suspend operator fun invoke(favoriteVacancyModel: FavoriteVacancyModel) =
        repository.addFavorite(favoriteVacancyModel)
}
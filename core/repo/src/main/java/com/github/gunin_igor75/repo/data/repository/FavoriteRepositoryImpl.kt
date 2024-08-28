package com.github.gunin_igor75.repo.data.repository

import com.github.gunin_igor75.repo.data.mappers.toFavoritesVacanciesModels
import com.github.gunin_igor75.repo.data.mappers.toVacancyDb
import com.github.gunin_igor75.common.base.model.FavoriteVacancyModel
import com.github.gunin_igor75.local.LocalSourceProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl(
    private val localDataSource: LocalSourceProvider,
) : com.github.gunin_igor75.repo.domain.repository.FavoriteRepository {
    override fun getFavoritesVacancies(): Flow<List<FavoriteVacancyModel>> {
        return localDataSource.getFavoritesVacancies().map { it.toFavoritesVacanciesModels() }
    }

    override fun observeIsFavorites(vacanciesId: String): Flow<Boolean> {
        return localDataSource.observeIsFavorites(vacanciesId)
    }

    override fun getCountFavorites(): Flow<Int> {
        return localDataSource.getCountFavorites()
    }

    override suspend fun addFavorite(favoriteVacancyModel: FavoriteVacancyModel) {
        localDataSource.addFavorite(favoriteVacancyModel.toVacancyDb())
    }

    override suspend fun removeFromFavorites(vacanciesId: String) {
        localDataSource.removeFromFavorites(vacanciesId)
    }
}
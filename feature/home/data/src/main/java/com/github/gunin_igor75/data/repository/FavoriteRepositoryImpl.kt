package com.github.gunin_igor75.data.repository

import com.github.gunin_igor75.data.mappers.toFavoritesVacanciesModels
import com.github.gunin_igor75.data.mappers.toVacancyDb
import com.github.gunin_igor75.domain.model.FavoriteVacancyModel
import com.github.gunin_igor75.domain.repository.FavoriteRepository
import com.github.gunin_igor75.local.LocalSourceProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl(
    private val localDataSource: LocalSourceProvider,
) : FavoriteRepository {
    override fun getFavoritesVacancies(): Flow<List<FavoriteVacancyModel>> {
        return localDataSource.getFavoritesVacancies().map { it.toFavoritesVacanciesModels() }
    }

    override fun observeIsFavorites(vacanciesId: String): Flow<Boolean> {
        return localDataSource.observeIsFavorites(vacanciesId)
    }

    override suspend fun addFavorite(favoriteVacancyModel: FavoriteVacancyModel) {
        localDataSource.addFavorite(favoriteVacancyModel.toVacancyDb())
    }

    override suspend fun removeFromFavorites(vacanciesId: String) {
        localDataSource.removeFromFavorites(vacanciesId)
    }
}
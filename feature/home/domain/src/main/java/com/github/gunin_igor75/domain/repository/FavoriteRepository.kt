package com.github.gunin_igor75.domain.repository

import com.github.gunin_igor75.domain.model.FavoriteVacancyModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavoritesVacancies(): Flow<List<FavoriteVacancyModel>>

    fun observeIsFavorites(vacanciesId: String): Flow<Boolean>

    suspend fun addFavorite(favoriteVacancyModel: FavoriteVacancyModel)

    suspend fun removeFromFavorites(vacanciesId: String)
}
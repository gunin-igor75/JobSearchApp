package com.github.gunin_igor75.local

import com.github.gunin_igor75.local.model.VacanciesDb
import kotlinx.coroutines.flow.Flow

interface LocalSourceProvider {

    fun getFavoritesVacancies(): Flow<List<VacanciesDb>>

    fun observeIsFavorites(vacanciesId: String): Flow<Boolean>

    suspend fun addFavorite(vacanciesDb: VacanciesDb)

    suspend fun removeFromFavorites(vacanciesId: String)
}
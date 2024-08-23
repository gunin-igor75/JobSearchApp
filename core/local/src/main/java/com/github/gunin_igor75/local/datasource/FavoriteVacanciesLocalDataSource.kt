package com.github.gunin_igor75.local.datasource

import com.github.gunin_igor75.local.LocalSourceProvider
import com.github.gunin_igor75.local.db.FavoritesVacanciesDao
import com.github.gunin_igor75.local.model.VacanciesDb
import kotlinx.coroutines.flow.Flow

class FavoriteVacanciesLocalDataSource(
    private val apiDao: FavoritesVacanciesDao,
) : LocalSourceProvider {
    override fun getFavoritesVacancies(): Flow<List<VacanciesDb>> {
        return apiDao.getFavoritesVacancies()
    }

    override fun observeIsFavorites(vacanciesId: String): Flow<Boolean> {
        return apiDao.observeIsFavorites(vacanciesId)
    }

    override suspend fun addFavorite(vacanciesDb: VacanciesDb) {
        apiDao.addFavorite(vacanciesDb)
    }

    override suspend fun removeFromFavorites(vacanciesId: String) {
        apiDao.removeFromFavorites(vacanciesId)
    }
}
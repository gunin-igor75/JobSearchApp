package com.github.gunin_igor75.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.gunin_igor75.local.model.VacanciesDb
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesVacanciesDao {

    @Query("SELECT * FROM vacancies")
    fun getFavoritesVacancies(): Flow<List<VacanciesDb>>

    @Query("SELECT EXISTS(SELECT * FROM vacancies WHERE id =:vacanciesId LIMIT 1)")
    fun observeIsFavorites(vacanciesId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(vacanciesDb: VacanciesDb)

    @Query("DELETE FROM vacancies WHERE id =:vacanciesId")
    suspend fun removeFromFavorites(vacanciesId: String)
}
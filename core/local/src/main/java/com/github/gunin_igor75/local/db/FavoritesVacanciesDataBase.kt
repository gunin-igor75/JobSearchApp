package com.github.gunin_igor75.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.gunin_igor75.local.model.VacanciesDb

@Database(
    entities = [
        VacanciesDb::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FavoritesVacanciesDataBase: RoomDatabase() {
    abstract fun favoritesVacanciesDao(): FavoritesVacanciesDao
}
package com.github.gunin_igor75.di

import android.app.Application
import androidx.room.Room
import com.github.gunin_igor75.common.base.utils.Constants.Companion.DB_NAME
import com.github.gunin_igor75.local.LocalSourceProvider
import com.github.gunin_igor75.local.datasource.FavoriteVacanciesLocalDataSource
import com.github.gunin_igor75.local.db.FavoritesVacanciesDao
import com.github.gunin_igor75.local.db.FavoritesVacanciesDataBase
import org.koin.dsl.module


internal fun providesDataBase(application: Application): FavoritesVacanciesDataBase {
    return Room.databaseBuilder(
        context = application,
        klass = FavoritesVacanciesDataBase::class.java,
        name = DB_NAME
    ).build()
}

internal fun providesFavoriteVacanciesDao(dataBase: FavoritesVacanciesDataBase) =
    dataBase.favoritesVacanciesDao()

internal val localDataSourceModule = module {
    single<FavoritesVacanciesDataBase> {
        providesDataBase(application = get())
    }
    single<FavoritesVacanciesDao> {
        providesFavoriteVacanciesDao(dataBase = get())
    }

    factory<LocalSourceProvider> {
        FavoriteVacanciesLocalDataSource(apiDao = get())
    }
}
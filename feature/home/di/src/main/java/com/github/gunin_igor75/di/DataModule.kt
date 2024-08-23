package com.github.gunin_igor75.di

import com.github.gunin_igor75.data.repository.FavoriteRepositoryImpl
import com.github.gunin_igor75.domain.repository.FavoriteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module


internal val dataModule = module {

    single<CoroutineScope> {
        CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    single<FavoriteRepository> {
        FavoriteRepositoryImpl(localDataSource = get())
    }
}
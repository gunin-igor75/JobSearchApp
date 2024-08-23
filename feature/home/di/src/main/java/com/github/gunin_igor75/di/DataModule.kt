package com.github.gunin_igor75.di

import com.github.gunin_igor75.common.base.model.DataResult
import com.github.gunin_igor75.data.repository.FavoriteRepositoryImpl
import com.github.gunin_igor75.data.repository.MainRepositoryImpl
import com.github.gunin_igor75.domain.repository.FavoriteRepository
import com.github.gunin_igor75.domain.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module


internal fun providesCoroutineScope(): CoroutineScope {
    return CoroutineScope(SupervisorJob() + Dispatchers.IO)
}

internal val dataModule = module {

    single<CoroutineScope> {
        providesCoroutineScope()
    }

    single<FavoriteRepository> {
        FavoriteRepositoryImpl(localDataSource = get())
    }

    single<MainRepository<DataResult<*>>> {
        MainRepositoryImpl(
            context = get(),
            repoFavorite = get(),
            networkSource = get(),
            coroutineScope = get()
        )
    }
}
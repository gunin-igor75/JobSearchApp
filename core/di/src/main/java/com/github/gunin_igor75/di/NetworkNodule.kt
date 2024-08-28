package com.github.gunin_igor75.di

import com.github.gunin_igor75.network.NetworkSource
import com.github.gunin_igor75.network.dataprovides.FakeNetworkSource
import com.github.gunin_igor75.network.dto.DataContainer
import org.koin.dsl.module

internal val networkModule = module {
    single<NetworkSource<DataContainer>> {
        FakeNetworkSource(context = get())
    }
}
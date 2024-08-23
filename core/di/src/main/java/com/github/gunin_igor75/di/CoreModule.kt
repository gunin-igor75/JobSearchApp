package com.github.gunin_igor75.di

import org.koin.dsl.module


val coreModule = module {
    includes(networkModule, localDataSourceModule)
}
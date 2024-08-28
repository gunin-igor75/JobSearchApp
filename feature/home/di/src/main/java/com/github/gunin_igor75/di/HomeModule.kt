package com.github.gunin_igor75.di

import org.koin.dsl.module

val homeModule = module {
    includes(dataModule, domainModule, presentationModule)
}
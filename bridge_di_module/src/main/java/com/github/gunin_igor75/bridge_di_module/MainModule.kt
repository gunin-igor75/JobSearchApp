package com.github.gunin_igor75.bridge_di_module

import com.github.gunin_igor75.di.coreModule
import com.github.gunin_igor75.di.homeModule
import org.koin.dsl.module


val mainModule = module {
    includes(coreModule, homeModule)
}
package com.github.gunin_igor75.jobsearchapp.app

import android.app.Application
import com.github.gunin_igor75.bridge_di_module.bridgeModule
import com.github.gunin_igor75.jobsearchapp.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class JobSearchApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@JobSearchApp)
            modules(listOf(bridgeModule, mainModule))
        }
    }
}
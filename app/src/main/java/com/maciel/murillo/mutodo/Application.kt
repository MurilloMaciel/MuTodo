package com.maciel.murillo.mutodo

import android.app.Application
import com.maciel.murillo.mutodo.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Application)
            modules(appComponent)
        }
    }
}
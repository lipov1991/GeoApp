package com.example.geoapp

import android.app.Application
import org.koin.core.context.GlobalContext.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(viewModelsModule, repositoriesModule, utilsModule)
        }
    }
}

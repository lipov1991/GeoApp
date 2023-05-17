package com.example.geoapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import com.example.geoapp.domain.utils.Fingerprint
import com.example.geoapp.domain.utils.PointFingerprints
import org.koin.core.context.GlobalContext.startKoin
import com.example.geoapp.domain.utils.LocationHandler


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(viewModelsModule, repositoriesModule, utilsModule))
        }

        LocationHandler(
            listOf(Fingerprint("1", -1.0, "1", 0)),
            listOf(PointFingerprints("1", -1.0, 0))
        )
    }
}
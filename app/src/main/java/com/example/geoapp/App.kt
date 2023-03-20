package com.example.geoapp

import android.app.Application
import com.example.geoapp.domain.utils.Fingerprint
import com.example.geoapp.domain.utils.PointFingerprints
import org.koin.core.context.GlobalContext.startKoin
import com.example.geoapp.domain.utils.PositioningUtils;
import com.example.geoapp.domain.utils.LocationHandler;


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(viewModelsModule, repositoriesModule, utilsModule)
        }
//        PositioningUtils().locateUserByRouters(listOf(listOf(10.0, 10.0, 10.0)))

        LocationHandler(
            listOf(Fingerprint("1", -1.0, "1", 0)),
            listOf(PointFingerprints("1", -1.0, 0))
        )
    }
}
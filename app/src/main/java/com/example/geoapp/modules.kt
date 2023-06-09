package com.example.geoapp

import com.example.geoapp.data.repository.fingerprint.FingerprintRepository
import com.example.geoapp.data.repository.fingerprint.SignalDatabase
import com.example.geoapp.domain.utils.EsriMapUtils
import com.example.geoapp.domain.utils.LocationHandler
import com.example.geoapp.domain.utils.PermissionUtils
import com.example.geoapp.ui.fingerprint.FingerprintViewModel
import com.example.geoapp.ui.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module


val repositoriesModule = module {
    single {
        val db = SignalDatabase.getDatabase(context = get())
        FingerprintRepository(db)
    }
}

val utilsModule = module {
    single { LocationHandler() }
    single { PermissionUtils() }
    single { EsriMapUtils() }
}

val viewModelsModule = module {
    viewModel { FingerprintViewModel(fingerprintRepository = get()) }
    viewModel { MapViewModel(compasUtils = get()) }
}
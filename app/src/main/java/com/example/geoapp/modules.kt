package com.example.geoapp


import com.example.geoapp.data.repository.AuthRepository
import com.example.geoapp.data.repository.FingerprintRepository
import com.example.geoapp.domain.utils.PositioningUtils
import com.example.geoapp.ui.auth.AuthViewModel
import com.example.geoapp.ui.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.geoapp.data.repository.db.signal.SignalDatabase
import com.example.geoapp.ui.fingerprint.FingerprintViewModel


val viewModelsModule = module {
    viewModel { AuthViewModel(authRepository = get()) }
    viewModel { FingerprintViewModel(fingerprintRepository = get()) }
    viewModel { MapViewModel(positioningUtils = get()) }
}

val repositoriesModule = module {
    single {
        val db = SignalDatabase.getDatabase(context = get())
        FingerprintRepository(db)
    }
    single { AuthRepository() }
}

val utilsModule = module {
    single { PositioningUtils() }
}

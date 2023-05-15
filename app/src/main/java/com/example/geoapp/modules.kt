package com.example.geoapp

import com.example.geoapp.data.repository.AuthRepository
import com.example.geoapp.domain.utils.FirebaseUtils
import com.example.geoapp.domain.utils.FacebookLoginUtils
import com.example.geoapp.domain.utils.PositioningUtils
import com.example.geoapp.ui.MainViewModel
import com.example.geoapp.ui.auth.AuthViewModel
import com.example.geoapp.ui.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelsModule = module {
    viewModel { AuthViewModel(FirebaseUtils = get()) }
    viewModel { MainViewModel(facebookLoginUtils = get()) }
    viewModel { MapViewModel(positioningUtils = get()) }
}

val repositoriesModule = module {
    single { AuthRepository() }
}

val utilsModule = module {
    single { FacebookLoginUtils() }
    single { PositioningUtils() }
    single { FirebaseUtils()}
}

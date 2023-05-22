package com.example.geoapp

import com.example.geoapp.data.repository.AuthRepository
import com.example.geoapp.domain.utils.FirebaseUtils
import com.example.geoapp.domain.utils.LocationHandler
import com.example.geoapp.ui.auth.AuthViewModel
import com.example.geoapp.ui.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelsModule = module {
    viewModel { AuthViewModel(FirebaseUtils = get()) }
    viewModel { MapViewModel() }
}

val repositoriesModule = module {
    single { AuthRepository() }
}

val utilsModule = module {
    single { FirebaseUtils() }
    single { LocationHandler() }
}

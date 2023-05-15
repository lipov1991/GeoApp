package com.example.geoapp

import android.content.Context
import android.net.wifi.WifiManager
import com.example.geoapp.data.repository.AuthRepository
import com.example.geoapp.domain.utils.WifiScanner
import com.example.geoapp.ui.MainViewModel
import com.example.geoapp.ui.auth.AuthViewModel
import com.example.geoapp.ui.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelsModule = module {
    viewModel { MainViewModel(authRepository = get(), wifiScanner = get()) }
    viewModel { AuthViewModel(authRepository = get()) }
    viewModel { MapViewModel() }
}

val repositoriesModule = module {
    single { AuthRepository() }
}

val utilsModule = module {
    single { WifiScanner(wifiManager = provideWiFiManager(context = get())) }
}

private fun provideWiFiManager(
    context: Context
): WifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager

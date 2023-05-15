package com.example.geoapp.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.geoapp.data.repository.AuthRepository
import com.example.geoapp.domain.utils.WifiScanner


// Tutaj będzie logika związana z MainActivity.
class MainViewModel(
    private val authRepository: AuthRepository,
    private val wifiScanner: WifiScanner
) : ViewModel() {

    fun startScanningIfHasPermissions(
        activity: Activity
    ) {
        wifiScanner.startScanningIfHasPermissions(activity)
    }

    fun startScanning() {
        wifiScanner.startScanning()
    }
}

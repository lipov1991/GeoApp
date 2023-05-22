package com.example.geoapp.ui

import androidx.lifecycle.ViewModel
import com.example.geoapp.data.repository.AuthRepository
import com.example.geoapp.data.repository.FingerprintRepository


// Tutaj będzie logika związana z MainActivity.
class MainViewModel(
    private val authRepository: AuthRepository,
    private val fingerprintRepository: FingerprintRepository
) : ViewModel() {

}

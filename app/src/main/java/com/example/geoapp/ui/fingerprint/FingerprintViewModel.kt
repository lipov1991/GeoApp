package com.example.geoapp.ui.fingerprint

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geoapp.data.repository.fingerprint.FingerprintRepository
import com.example.geoapp.data.repository.fingerprint.SignalEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FingerprintViewModel(
    private val fingerprintRepository: FingerprintRepository
) : ViewModel() {

    fun saveSignal() = viewModelScope.launch(Dispatchers.IO) {
        val signal = SignalEntity(0, "jakies ssid", 100)
        fingerprintRepository.saveSignal(signal)
    }

    fun deleteSignal() = viewModelScope.launch(Dispatchers.IO) {
        val signal = SignalEntity(1, "jakies ssid", 100)
        fingerprintRepository.deleteSignal(signal)
    }

    fun getAllSignals() = viewModelScope.launch(Dispatchers.IO) {
        fingerprintRepository.getAllSignal()?.forEach { signal ->
            Log.d("signal_info", "${signal.id}: ${signal.ssid}, ${signal.strength}")
        }
    }
}

package com.example.geoapp.ui.map

import androidx.lifecycle.ViewModel
import com.example.geoapp.domain.utils.PositioningUtils
import com.example.geoapp.domain.utils.MapUtils


// Tutaj będzie logika związana z głównym ekranem aplikacji (mapa budynku).
class MapViewModel(
    private val positioningUtils: PositioningUtils,
    private val mapUtils: MapUtils
) : ViewModel() {

    fun locateUser() {
        positioningUtils.locateUser()
    }

    fun setUpAPI() {
        mapUtils.setApiKeyForApp()
    }
}

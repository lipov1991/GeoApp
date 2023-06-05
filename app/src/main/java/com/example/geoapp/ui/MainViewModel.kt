package com.example.geoapp.ui

import androidx.lifecycle.ViewModel
import com.esri.arcgisruntime.mapping.BasemapStyle
import com.example.geoapp.data.repository.AuthRepository
import com.example.geoapp.domain.utils.MapUtils


// Tutaj będzie logika związana z MainActivity.
class MainViewModel(
    private val mapUtils: MapUtils
) : ViewModel() {

    fun setMapStyle(mapStyle: BasemapStyle) = mapUtils.setMapStyle(mapStyle)
}

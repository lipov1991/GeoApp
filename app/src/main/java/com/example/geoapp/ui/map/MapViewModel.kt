package com.example.geoapp.ui.map

import androidx.lifecycle.ViewModel
import com.example.geoapp.domain.utils.EsriMap
import com.example.geoapp.domain.utils.PositioningUtils



class MapViewModel(
    private val positioningUtils: PositioningUtils,

    private val EsriMap: EsriMap
) : ViewModel() {

    fun locateUser() {
        positioningUtils.locateUser()
    }
}

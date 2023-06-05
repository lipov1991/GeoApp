package com.example.geoapp.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.BasemapStyle
import com.example.geoapp.domain.utils.MapUtils

class MapViewModel(
    private val mapUtils: MapUtils
) : ViewModel() {

    val arcGISMap: ArcGISMap
        get() = mapUtils.arcGISMap
    val selectedMapStyle: LiveData<BasemapStyle>
        get() = mapUtils.selectedMapStyle

    fun setApiKeyForApp() = mapUtils.setApiKeyForApp()
}

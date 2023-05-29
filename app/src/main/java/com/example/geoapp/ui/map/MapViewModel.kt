package com.example.geoapp.ui.map

import androidx.lifecycle.ViewModel
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.Viewpoint
import com.example.geoapp.domain.utils.MapUtils

class MapViewModel(
    private val mapUtils: MapUtils
) : ViewModel() {

    val arcGISMap: ArcGISMap
        get() = mapUtils.arcGISMap
    val viewpoint: Viewpoint
        get() = mapUtils.viewpoint

    fun setApiKeyForApp() = mapUtils.setApiKeyForApp()
}

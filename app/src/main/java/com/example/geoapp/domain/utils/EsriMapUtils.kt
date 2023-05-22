package com.example.geoapp.domain.utils

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.BasemapStyle
import com.esri.arcgisruntime.mapping.Viewpoint


class EsriMapUtils {

    companion object {
        private const val API_KEY = "AAPKb99268c7e4c04feb9ca950d1c3c2104a_tzSjweNo1TJkblCw6LP3ikXShnl1W3NqQfyfDv7bF-EcJv9sYyWEpsahx-zNyXr"
    }

    val map: ArcGISMap
        get() = ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC)
    val viewpoint: Viewpoint = Viewpoint(52.2205593, 21.0101898, 5000.0)

    fun setApiKey() {
        // set your API key
        // Note: it is not best practice to store API keys in source code. The API key is referenced
        // here for the convenience of this tutorial.
        ArcGISRuntimeEnvironment.setApiKey(API_KEY)
    }
}
package com.example.geoapp.domain.utils

import android.content.Context
import android.provider.Settings.Global.getString
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.BasemapStyle
import com.esri.arcgisruntime.mapping.Viewpoint


class MapUtils {

    companion object {
        private const val api = "AAPK7f939d2d3f0b497b9f91c9df88bb4a1eCzk-ExdqUew-i8ZKRfbwzaBt0q-eiKPemx9eIxBiRvXlpPMDMjMBIWUc1js1PMX8"
        private const val license = "runtimelite,1000,rud3084639497,none,6PB3LNBHPFJPA1GJH148"
    }

    val arcGISMap: ArcGISMap
        get() = ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC)
    val viewpoint: Viewpoint
        get() = Viewpoint(52.22065508457322, 21.009935693065543, 2000.0)
    fun setApiKeyForApp() {
        ArcGISRuntimeEnvironment.setLicense(license)
        ArcGISRuntimeEnvironment.setApiKey(api)
    }
}

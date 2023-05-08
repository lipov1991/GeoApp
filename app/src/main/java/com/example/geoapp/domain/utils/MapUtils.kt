package com.example.geoapp.domain.utils

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.BasemapStyle


class MapUtils {

    val arcGISMap: ArcGISMap
        get() = ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC)


    fun setApiKeyForApp() {
        // TODO Move below hardcoded texts to strings.
        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud3084639497,none,6PB3LNBHPFJPA1GJH148")
        ArcGISRuntimeEnvironment.setApiKey("AAPK7f939d2d3f0b497b9f91c9df88bb4a1eCzk-ExdqUew-i8ZKRfbwzaBt0q-eiKPemx9eIxBiRvXlpPMDMjMBIWUc1js1PMX8")
    }
}

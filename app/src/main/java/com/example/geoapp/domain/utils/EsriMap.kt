package com.example.geoapp.domain.utils

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.BasemapStyle
import com.esri.arcgisruntime.mapping.Viewpoint
import com.esri.arcgisruntime.mapping.view.MapView
import com.esri.arcgisruntime.ogc.wms.WmsService


class EsriMap (
    context:refacContext
        ){
    val mapView: MapView? = null

    fun setupMap() {

        // create a map with the BasemapStyle streets
        val map = ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC)

        // set the map to be displayed in the layout's MapView
        val mapView = view?.findViewById<MapView>(R.id.mapView)
        mapView?.map = map

        // set the viewpoint, Viewpoint(latitude, longitude, scale)
        mapView?.setViewpoint(Viewpoint(52.2205593, 21.0101898, 5000.0))

    }

    fun setApiKeyForApp() {
        // set your API key
        // Note: it is not best practice to store API keys in source code. The API key is referenced
        // here for the convenience of this tutorial.
        ArcGISRuntimeEnvironment.setApiKey(getString(R.string.api))

    }

}
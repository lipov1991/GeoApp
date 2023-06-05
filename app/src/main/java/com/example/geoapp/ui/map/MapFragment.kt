package com.example.geoapp.ui.map

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.esri.arcgisruntime.mapping.Viewpoint
import com.esri.arcgisruntime.mapping.view.MapView
import com.example.geoapp.R
import com.example.geoapp.ui.MapActionsCallback
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapFragment : Fragment() {

    private val mapView: MapView? = null

    private val viewModel: MapViewModel by viewModel()
    private var mapActionsCallback: MapActionsCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MapActionsCallback) {
            mapActionsCallback = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_map, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setApiKeyForApp()
        setupMap()
        view.findViewById<ImageButton>(R.id.mapstylebutton).setOnClickListener {
            mapActionsCallback?.onChangeMapStyleButtonClick(it)
        }
        viewModel.selectedMapStyle.observe(::getLifecycle) {
            setupMap()
        }
    }

    // set up your map here. You will call this method from onCreate()
    private fun setupMap() {
        // create a map with the BasemapStyle streets
        // set the map to be displayed in the layout's MapView
        val mapView = view?.findViewById<MapView>(R.id.mapView)
        mapView?.map = viewModel.arcGISMap
        // set the viewpoint, Viewpoint(latitude, longitude, scale)
        // TODO Move below values to consts..
        mapView?.setViewpoint(Viewpoint(52.22065508457322, 21.009935693065543, 2000.0))
    }

    override fun onPause() {
        mapView?.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView?.resume()
    }

    override fun onDestroy() {
        mapView?.dispose()
        super.onDestroy()
    }
}

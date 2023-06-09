package com.example.geoapp.ui.map


// importy i wszystko co z arcgisem do pliku arcgis utils!!!
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.esri.arcgisruntime.mapping.view.MapView
import com.example.geoapp.R
import com.example.geoapp.data.repository.buildingrepository
import com.example.geoapp.domain.utils.EsriMapUtils
import org.koin.android.ext.android.inject


class MapFragment : Fragment() {

    private val mapView: MapView? = null

    // wstrzykiwanie viemodela (on jest do logiki biznesowej)
    private val esriMapUtils: EsriMapUtils by inject()

    private val viewModel: MapViewModel by viewModels()

    private var currentDegree = 0f
    private var lastUpdatedTime = System.currentTimeMillis()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_map, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        esriMapUtils.setApiKey()
        setupMap()

        val floorsTry = buildingrepository()
        view.findViewById<RecyclerView>(R.id.my_recycler_view).adapter = FloorAdapter(floorsTry.getFloors())
        viewModel.azimuthInDegrees.observe(::getLifecycle) { azimuthInDegrees ->
            val imageview = view.findViewById<ImageView>(R.id.compass_iv)
            val rotateAnimation = RotateAnimation(
                currentDegree, -azimuthInDegrees, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f
            )
            rotateAnimation.duration = 250
            rotateAnimation.fillAfter = true
            imageview!!.startAnimation(rotateAnimation)
            currentDegree = -azimuthInDegrees
            lastUpdatedTime = System.currentTimeMillis()
        }
    }

    private fun setupMap() {
        val mapView = view?.findViewById<MapView>(R.id.mapView)
        mapView?.map = esriMapUtils.map
        mapView?.setViewpoint(esriMapUtils.viewpoint)
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
package com.example.geoapp.ui.map


// importy i wszystko co z arcgisem do pliku arcgis utils!!!
import android.widget.Button
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.esri.arcgisruntime.mapping.view.MapView
import com.example.geoapp.R
import com.example.geoapp.data.repository.buildingrepository
import com.example.geoapp.domain.utils.EsriMapUtils
import org.koin.android.ext.android.inject
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class MapFragment : Fragment() {

    private val mapView: MapView? = null
    val barcodeLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(
        ScanContract()
    ) { result: ScanIntentResult ->
        viewModel.handleQrCode(result, requireContext())
    }

    // wstrzykiwanie viemodela (on jest do logiki biznesowej)
    private val esriMapUtils: EsriMapUtils by inject()
    private val viewModel: MapViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_map, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        esriMapUtils.setApiKey()
        setupMap()
        val floorsTry = buildingrepository()
        view.findViewById<RecyclerView>(R.id.my_recycler_view).adapter = FloorAdapter(floorsTry.getFloors())
        view.findViewById<Button>(R.id.qr_scanner).setOnClickListener{
            barcodeLauncher.launch(ScanOptions())
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
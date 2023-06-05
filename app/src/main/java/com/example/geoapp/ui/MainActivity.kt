package com.example.geoapp.ui

import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.esri.arcgisruntime.mapping.BasemapStyle
import com.example.geoapp.R
import com.example.geoapp.databinding.ActivityAuthBinding
import com.example.geoapp.ui.map.MapFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MapActionsCallback {

    private val viewModel: MainViewModel by viewModel()
    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MapFragment())
            .commit()
    }

    override fun onChangeMapStyleButtonClick(view: View, ) {
        val popup = PopupMenu(this, view)
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item1 -> viewModel.setMapStyle(BasemapStyle.ARCGIS_TOPOGRAPHIC  )
                R.id.item2 -> viewModel.setMapStyle(BasemapStyle.OSM_STREETS)
                R.id.item3 -> viewModel.setMapStyle(BasemapStyle.ARCGIS_TERRAIN)
                R.id.item4 -> viewModel.setMapStyle(BasemapStyle.ARCGIS_MIDCENTURY)
                R.id.item5 -> viewModel.setMapStyle(BasemapStyle.ARCGIS_NAVIGATION)
                else -> viewModel.setMapStyle(BasemapStyle.ARCGIS_TOPOGRAPHIC)
            }
            true
        }
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.map_menu, popup.menu)
        popup.show()
    }
}

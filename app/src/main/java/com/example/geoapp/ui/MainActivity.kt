package com.example.geoapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geoapp.R
import com.example.geoapp.domain.utils.LocationHandler
import com.example.geoapp.domain.utils.PermissionUtils
import com.example.geoapp.domain.utils.PointFingerprints
import com.example.geoapp.ui.map.MapFragment
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val locationHandler: LocationHandler by inject()
    private val permissionUtils: PermissionUtils by inject()

    companion object {
        private const val REQUEST_CODE_LOCATION_PERMISSIONS = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MapFragment()).commit()
        permissionUtils.requestPermissionIfNeeded(android.Manifest.permission.ACCESS_COARSE_LOCATION, REQUEST_CODE_LOCATION_PERMISSIONS, this)
        fingerprintTest()
    }

    private fun fingerprintTest() {
        locationHandler.sortFingerprints()
        val groupedRouters: List<PointFingerprints> = locationHandler.getAverageRouterPer4Directions()
        val closestRoom: String = locationHandler.calculateDistance(groupedRouters, locationHandler.routers)
        Log.d("TestData", String.format("Najblizszy pokój: %s", closestRoom))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissionUtils.permissionGranted(requestCode, REQUEST_CODE_LOCATION_PERMISSIONS, grantResults)) {
            Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "App may not work properly", Toast.LENGTH_SHORT).show()
        }
    }
}

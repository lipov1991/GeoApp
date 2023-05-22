package com.example.geoapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geoapp.R
import com.example.geoapp.databinding.ActivityAuthBinding
import com.example.geoapp.domain.utils.LocationHandler
import com.example.geoapp.domain.utils.PointFingerprints
import com.example.geoapp.ui.auth.AuthFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityAuthBinding
    private val obj: LocationHandler by inject()
    private val permissionUtils: PermissionUtils by inject()

    companion object{
        private const val REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, AuthFragment()).commit()
        permissionUtils.requestPermissionIfNeeded(android.Manifest.permission.ACCESS_COARSE_LOCATION, REQUEST_CODE, this)

        obj.sortFingerprints()
        val groupedRouters: List<PointFingerprints> = obj.getAverageRouterPer4Directions()
        val closestRoom: String = obj.calculateDistance(groupedRouters, obj.routers)
        Log.d("TestData", String.format("Najblizszy pokój: %s", closestRoom))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissionUtils.permissionGranted(requestCode, REQUEST_CODE, grantResults)){
            Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "App may not work properly", Toast.LENGTH_SHORT).show()
        }
    }
}

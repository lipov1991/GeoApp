package com.example.geoapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.geoapp.R
import com.example.geoapp.domain.utils.PointFingerprints
import com.example.geoapp.domain.utils.LocationHandler
import com.example.geoapp.ui.auth.AuthFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.geoapp.domain.utils.TestData
import com.example.geoapp.databinding.ActivityAuthBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, AuthFragment()).commit()

        val test = TestData()
        val obj = LocationHandler(test.getFingerprints(), test.getPointfingerprints())

        obj.sortFingerprints()
        val groupedRouters: List<PointFingerprints> = obj.getAverageRouterPer4Directions()
        val closestRoom: String = obj.calculateDistance(groupedRouters, obj.routers)
        Log.d("TestData", String.format("Najblizszy pokój: %s", closestRoom))
    }
}

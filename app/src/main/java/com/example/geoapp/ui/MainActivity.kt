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


// Tu będziemy decydować, co wyśweitlić jako pierwsze - ekran logowania czy główny ekran (w przypadku aktywnej sesji).
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

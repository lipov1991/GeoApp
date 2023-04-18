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

        var test = TestData()
        var obj = LocationHandler(test.get_Fingerprints(), test.get_PointFingerprints())

        obj.sort_fingerprints()
        var grouped_routers: List<PointFingerprints> = obj.get_average_router_per_4_directions()
        var closest_room: String = obj.calculate_distance(grouped_routers, obj.rooms)
        Log.d("TestData", String.format("Najblizszy pokój: %s", closest_room));

    }
}

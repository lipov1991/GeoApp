package com.example.geoapp.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geoapp.R
import com.example.geoapp.domain.utils.WifiScanner.Companion.REQUEST_CODE_SCAN_WIFI_PERMISSIONS
import com.example.geoapp.ui.auth.AuthFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


// Tu będziemy decydować, co wyśweitlić jako pierwsze - ekran logowania czy główny ekran (w przypadku aktywnej sesji).
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, AuthFragment()).commit()
        viewModel.startScanningIfHasPermissions(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_SCAN_WIFI_PERMISSIONS && grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
            viewModel.startScanning()
        } else {
            Toast.makeText(this, "Bez przydzielenia niebędnych uprawnień aplikacje nie będzie działać prawidłowo.", Toast.LENGTH_LONG).show()
        }
    }
}

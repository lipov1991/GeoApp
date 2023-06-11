package com.example.geoapp.ui.map

import androidx.lifecycle.ViewModel
import android.content.Context
import android.widget.Toast
import com.example.geoapp.domain.model.Position
import com.google.gson.Gson
import com.journeyapps.barcodescanner.ScanIntentResult



class MapViewModel(
) : ViewModel() {

    fun handleQrCode(result: ScanIntentResult?, context: Context) {
        if (result?.contents == null) {
            Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            val gson = Gson()
            val position = gson.fromJson(result.contents, Position::class.java)
            Toast.makeText(
                context,
                "Latitude: " + position.lat + "\n" + "Longitude: " + position.lon,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

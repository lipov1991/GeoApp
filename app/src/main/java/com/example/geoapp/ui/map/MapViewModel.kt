package com.example.geoapp.ui.map

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.geoapp.domain.utils.CompasUtils


class MapViewModel(private val compasUtils: CompasUtils) : ViewModel() {

    val azimuthInDegrees: LiveData<Float>
        get() = compasUtils.azimuthInDeegres

    fun setUpCompass(context: Context) = compasUtils.setUpCompas(context)
    fun unregisterlistener() = compasUtils.unregisterlistener()
}
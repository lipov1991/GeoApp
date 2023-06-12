package com.example.geoapp.domain.utils

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geoapp.R

class CompasUtils: SensorEventListener {

    val azimuthInDeegres: LiveData<Float>
        get() = _azimuthInDegrees
    private var _azimuthInDegrees = MutableLiveData<Float>()

    var imageview: ImageView? = null
    var sensorManager: SensorManager? = null
    var accelometer: Sensor? = null
    var magnetometrer: Sensor? = null
    var lastAccelerometer = FloatArray(3)
    var lastMagnetometer = FloatArray(3)
    var rotationMatrix = FloatArray(9)
    var orientation = FloatArray(3)
    var isLastAccelerometerArrayCopied = false
    var isLastMagnetometerArrayCopied = false
    var lastUpdatedTime: Long = 0
    var currentDegree = 0f


    fun setUpCompas(context: Context) {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magnetometrer = sensorManager?.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        sensorManager?.registerListener(this, accelometer, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager?.registerListener(this, magnetometrer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun unregisterlistener(){
        sensorManager?.unregisterListener(this, accelometer)
        sensorManager?.unregisterListener(this, magnetometrer)
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        if (sensorEvent.sensor == accelometer) {
            System.arraycopy(sensorEvent.values, 0, lastAccelerometer, 0, sensorEvent.values.size)
            isLastAccelerometerArrayCopied = true
        } else if (sensorEvent.sensor == magnetometrer) {
            System.arraycopy(sensorEvent.values, 0, lastMagnetometer, 0, sensorEvent.values.size)
            isLastMagnetometerArrayCopied = true
        }
        if (isLastAccelerometerArrayCopied && isLastMagnetometerArrayCopied && (System.currentTimeMillis() - lastUpdatedTime > 250)) {
            SensorManager.getRotationMatrix(rotationMatrix, null, lastAccelerometer, lastMagnetometer)
            SensorManager.getOrientation(rotationMatrix, orientation)
            val azimuthinRadians = orientation[0]
            val azimuthinDegrees = Math.toDegrees(azimuthinRadians.toDouble()).toFloat()
            val rotateAnimation = RotateAnimation(
                currentDegree,
                -azimuthinDegrees,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            rotateAnimation.duration = 250
            rotateAnimation.fillAfter = true
            imageview!!.startAnimation(rotateAnimation)
            currentDegree = -azimuthinDegrees
            lastUpdatedTime = System.currentTimeMillis()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

}
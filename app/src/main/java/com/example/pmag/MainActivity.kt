package com.example.pmag

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {
    private var imageview: ImageView? = null
    private var sensormanager: SensorManager? = null
    private var accelometerSensor: Sensor? = null
    private var magnetometrerSensor: Sensor? = null
    private var lastAccelerometer = FloatArray(3)
    private var lastMagnetometer = FloatArray(3)
    private var rotationMatrix = FloatArray(9)
    private var orientation = FloatArray(3)
    var isLastAccelerometerArrayCopied = false
    var isLastMagnetometerArrayCopied = false
    var lastUpdatedTime: Long = 0
    var currentDegree = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        imageview = findViewById<ImageView>(R.id.imgCompas)
        sensormanager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelometerSensor = sensormanager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magnetometrerSensor = sensormanager!!.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        if (sensorEvent.sensor == accelometerSensor) {
            System.arraycopy(sensorEvent.values, 0, lastAccelerometer, 0, sensorEvent.values.size)
            isLastAccelerometerArrayCopied = true
        } else if (sensorEvent.sensor == magnetometrerSensor) {
            System.arraycopy(sensorEvent.values, 0, lastMagnetometer, 0, sensorEvent.values.size)
            isLastMagnetometerArrayCopied = true
        }
        if (isLastAccelerometerArrayCopied && isLastMagnetometerArrayCopied && (System.currentTimeMillis() - lastUpdatedTime > 250)) {
            SensorManager.getRotationMatrix(
                rotationMatrix,
                null,
                lastAccelerometer,
                lastMagnetometer
            )
            SensorManager.getOrientation(rotationMatrix, orientation)
            var azimuthinRadians = orientation[0]
            var azimuthinDegrees = Math.toDegrees(azimuthinRadians.toDouble()).toFloat()
            var rotateAnimation = RotateAnimation(
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

    override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
    override fun onResume() {
        super.onResume()
        sensormanager!!.registerListener(
            this as SensorEventListener,
            accelometerSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
        sensormanager!!.registerListener(
            this as SensorEventListener,
            magnetometrerSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        sensormanager!!.unregisterListener(this as SensorEventListener, accelometerSensor)
        sensormanager!!.unregisterListener(this as SensorEventListener, magnetometrerSensor)
    }
}
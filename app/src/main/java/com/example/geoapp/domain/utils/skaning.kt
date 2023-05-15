package com.example.geoapp.domain.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Handler
import android.os.Looper

class WifiScanner(private val context: Context) {

    private val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    private val scanResults = mutableListOf<String>()
    private var isScanning = false
    private var scanStartTime: Long = 0

    private val scanRunnable = Runnable {
        startScanInternal()
    }

    companion object {
        private const val SCAN_INTERVAL_MS = 5000L
    }



    fun startScan() {
        if (!isScanning) {
            isScanning = true
            scanStartTime = System.currentTimeMillis()
            startScanInternal()
        }
    }

    private fun startScanInternal() {
        wifiManager.startScan()
        scanResults.clear()
        // nie wiemy tu
        val results =
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return
            }
            wifiManager.scanResults

        // do tego
        for (result in results) {
            scanResults.add(result.SSID)
        }
        isScanning = false
        val elapsed = System.currentTimeMillis() - scanStartTime
        if (elapsed < SCAN_INTERVAL_MS) {
            Handler(Looper.getMainLooper()).postDelayed(scanRunnable, SCAN_INTERVAL_MS - elapsed)
        } else {
            startScanInternal()
        }
    }

    fun getScanResults(): List<String> {
        return scanResults.toList()
    }

}




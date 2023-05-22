package com.example.geoapp.ui

import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log

class PermissionUtils{
    companion object{
        private const val TAG = "pw.Permissions"
    }

    fun requestPermissionIfNeeded(
        permission: String,
        requestcode: Int,
        activity: Activity
    ) {
        if (activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Already granted")
        } else {
            activity.requestPermissions(arrayOf(permission), requestcode
            )
        }
    }

    fun permissionGranted(
        requestcode: Int,
        expectedrequestcode: Int,
        grantResults: IntArray
    ): Boolean = requestcode == expectedrequestcode && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
}
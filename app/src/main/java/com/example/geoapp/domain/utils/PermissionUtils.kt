package com.example.geoapp.domain.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log

class PermissionUtils {

    companion object {
        private const val TAG = "pw.Permissions"
    }

    fun requestPermissionIfNeeded(
        permission: String,
        requestCode: Int,
        activity: Activity
    ) {
        if (activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Already granted")
        } else {
            activity.requestPermissions(arrayOf(permission), requestCode)
        }
    }

    fun permissionGranted(
        requestCode: Int,
        expectedRequestCode: Int,
        grantResults: IntArray
    ): Boolean = requestCode == expectedRequestCode && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
}
package it.codingjam.cleanweather.main

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import it.codingjam.cleanweather.domain.OpenForTesting

private const val PERMISSIONS_REQUEST_LOCATION = 123

@OpenForTesting
class PermissionManager {

    fun checkLocationPermission(activity: Activity): Boolean {
        return if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    PERMISSIONS_REQUEST_LOCATION)
            false
        } else {
            true
        }
    }

    fun onRequestPermissionsResult(activity: Activity, requestCode: Int, permissions: Array<out String>, grantResults: IntArray): Boolean {
        if (requestCode == PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.getOrNull(0) == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    return true
                }
            }
        }
        return false
    }
}
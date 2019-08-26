package it.codingjam.cleanweather.position

import android.app.Application
import it.codingjam.cleanweather.domain.LocationManager
import it.codingjam.cleanweather.kotlinutils.getOrCreate
import it.codingjam.cleanweather.utils.ComponentHolderApp

interface LocationComponent {
    val locationManager: LocationManager
}

private class LocationComponentImpl(application: Application) : LocationComponent {
    override val locationManager by lazy {
        AndroidLocationManager(application)
    }
}

fun ComponentHolderApp.locationComponent(): LocationComponent = getOrCreate {
    LocationComponentImpl(this)
}
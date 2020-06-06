package it.codingjam.cleanweather.position

import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import com.codingjam.cleanweather.entities.City
import com.codingjam.cleanweather.entities.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import it.codingjam.cleanweather.domain.LocationManager
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Singleton
class AndroidLocationManager @Inject constructor(context: Application) : LocationManager {

    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    private val geocoder = Geocoder(context, Locale.getDefault())

    @SuppressLint("MissingPermission")
    override suspend fun getLastLocation(): Location = suspendCoroutine { continuation ->
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location == null)
                        continuation.resumeWithException(Exception("Location not available"))
                    else
                        continuation.resume(Location(location.latitude, location.longitude))
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
    }

    override suspend fun getCities(location: Location): List<City> = withContext(Dispatchers.IO) {
        geocoder.getFromLocation(location.lat, location.lon, 10)
                .filter { it.locality != null }
                .map {
                    City(it.locality, it.countryCode)
                }
    }
}
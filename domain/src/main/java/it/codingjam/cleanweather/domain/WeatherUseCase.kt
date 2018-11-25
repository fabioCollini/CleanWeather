package it.codingjam.cleanweather.domain

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class WeatherUseCase(
        private val locationManager: LocationManager,
        private val repository: TemperatureRepository) {

    suspend fun getCityData(): String = coroutineScope {
        try {
            val location = locationManager.getLastLocation()

            val cities = async { locationManager.getCities(location) }

            val temperature = repository.getTemperature(location.lat, location.lon)

            val city = cities.await().getOrElse(0) { "No city found" }
            "$city \n $temperature"
        } catch (e: Exception) {
            "Error retrieving data: ${e.message}"
        }
    }
}
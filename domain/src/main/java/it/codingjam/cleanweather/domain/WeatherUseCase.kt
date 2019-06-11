package it.codingjam.cleanweather.domain

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

@OpenForTesting
@DomainSingleton
class WeatherUseCase @Inject constructor(
        private val locationManager: LocationManager,
        private val repository: TemperatureRepository) {

    suspend fun getCityData(): String = try {
        coroutineScope {
            val location = locationManager.getLastLocation()

            val cities = async { locationManager.getCities(location) }

            val temperature = repository.getTemperature(location.lat, location.lon)

            val city = cities.await().getOrElse(0) { "No city found" }
            "$city \n $temperature"
        }
    } catch (e: Exception) {
        "Error retrieving data: ${e.message}"
    }

    suspend fun getForecast(): String = coroutineScope {
        try {
            val location = locationManager.getLastLocation()

            val temperature = repository.getForecast(location.lat, location.lon)

            temperature.joinToString("\n")
        } catch (e: Exception) {
            "Error retrieving data: ${e.message}"
        }
    }
}
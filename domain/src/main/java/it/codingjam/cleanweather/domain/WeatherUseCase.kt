package it.codingjam.cleanweather.domain

import it.codingjam.cleanweather.city.CityRetriever
import it.codingjam.cleanweather.weather.WeatherRepository

class WeatherUseCase(
        private val cityRetriever: CityRetriever,
        private val weatherRepository: WeatherRepository) {

    suspend fun getCityData(s: String): String {
        return try {
            val cities = cityRetriever.findCity(s)

            if (cities.isEmpty()) {
                "No city found"
            } else {
                cities.map { it to weatherRepository.getWeather(it.id) }
                        .joinToString("\n") { (city, weather) ->
                            "$city - $weather"
                        }
            }
        } catch (e: Exception) {
            "Error retrieving data: ${e.message}"
        }
    }
}
package it.codingjam.cleanweather.domain

import com.codingjam.cleanweather.entities.City
import com.codingjam.cleanweather.entities.Weather
import it.codingjam.cleanweather.city.CityRetriever
import it.codingjam.cleanweather.weather.WeatherRepository

class WeatherUseCase(
        private val cityRetriever: CityRetriever,
        private val weatherRepository: WeatherRepository) {

    suspend fun getCityData(s: String): String {
        val cities = cityRetriever.findCity(s)

        return if (cities.isEmpty()) {
            "No city found"
        } else {
            cities.map { it to weatherRepository.getWeather(it.id) }
                    .joinToString("\n") { (city, weather) ->
                        formatResult(city, weather)
                    }
        }
    }

    private fun formatResult(city: City, weather: Weather): String {
        return """${city.name} - ${city.country}
            |${weather.currentTemperature}º min ${weather.forecastMin}º max ${weather.forecastMax}º""".trimMargin()
    }
}
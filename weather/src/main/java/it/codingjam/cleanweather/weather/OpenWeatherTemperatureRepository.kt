package it.codingjam.cleanweather.weather

import com.codingjam.cleanweather.entities.Temperature
import it.codingjam.cleanweather.api.WeatherApi
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import it.codingjam.cleanweather.domain.TemperatureRepository

class OpenWeatherTemperatureRepository(
        private val api: WeatherApi
) : TemperatureRepository {

    override suspend fun getTemperature(lat: Double, lon: Double): Temperature = coroutineScope {
        val forecastDeferred = async { api.forecast(lat, lon) }
        val weather = api.currentWeather(lat, lon)
        val temperatures = forecastDeferred.await().list.map { it.main }

        Temperature(
                weather.main.temp.toInt(),
                temperatures.map { it.temp_min }.min()?.toInt(),
                temperatures.map { it.temp_max }.max()?.toInt()
        )
    }
}
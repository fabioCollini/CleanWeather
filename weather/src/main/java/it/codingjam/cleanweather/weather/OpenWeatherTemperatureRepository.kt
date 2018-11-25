package it.codingjam.cleanweather.weather

import com.codingjam.cleanweather.entities.Temperature
import it.codingjam.cleanweather.api.WeatherApi

class OpenWeatherTemperatureRepository(
        private val api: WeatherApi
) : TemperatureRepository {

    override suspend fun getTemperature(lat: Double, lon: Double): Temperature {
        val forecastDeferred = api.forecast(lat, lon)
        val weather = api.currentWeather(lat, lon).await()
        val temperatures = forecastDeferred.await().list.map { it.main }

        return Temperature(
                weather.main.temp.toInt(),
                temperatures.map { it.temp_min }.min()?.toInt(),
                temperatures.map { it.temp_max }.max()?.toInt()
        )
    }
}
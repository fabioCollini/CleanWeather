package it.codingjam.cleanweather.weather

import com.codingjam.cleanweather.entities.Temperature
import it.codingjam.cleanweather.domain.TemperatureRepository

class OpenWeatherTemperatureRepository(
        private val api: WeatherApi
) : TemperatureRepository {

    override suspend fun getTemperature(cityId: Int): Temperature {
        val forecastDeferred = api.forecast(cityId)
        val weather = api.currentWeather(cityId).await()
        val temperatures = forecastDeferred.await().list.map { it.main }

        return Temperature(
                weather.main.temp.toInt(),
                temperatures.map { it.temp_min }.min()?.toInt(),
                temperatures.map { it.temp_max }.max()?.toInt()
        )
    }
}
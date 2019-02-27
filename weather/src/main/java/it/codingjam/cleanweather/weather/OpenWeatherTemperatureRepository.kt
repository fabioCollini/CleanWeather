package it.codingjam.cleanweather.weather

import com.codingjam.cleanweather.entities.Temperature
import it.codingjam.cleanweather.domain.TemperatureRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OpenWeatherTemperatureRepository @Inject constructor(
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
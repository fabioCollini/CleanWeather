package it.codingjam.cleanweather.weather

import com.codingjam.cleanweather.entities.Temperature
import it.codingjam.cleanweather.domain.TemperatureRepository
import javax.inject.Inject

@WeatherSingleton
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

    override suspend fun getForecast(lat: Double, lon: Double): List<Temperature> {
        val temperatures = api.forecast(lat, lon).await().list.map { it.main }

        return temperatures.map {
            Temperature(
                    it.temp.toInt(),
                    it.temp_min.toInt(),
                    it.temp_max.toInt()
            )
        }
    }
}
package it.codingjam.cleanweather.weather

import com.codingjam.cleanweather.entities.Weather
import it.codingjam.cleanweather.api.WeatherApi

class WeatherRepository(private val api: WeatherApi) {
    suspend fun getWeather(cityId: Int): Weather {
        val forecastDeferred = api.forecast(cityId)
        val weather = api.currentWeather(cityId).await()
        val temperatures = forecastDeferred.await().list.map { it.main }

        return Weather(
                weather.weather[0].description,
                weather.main.temp.toInt(),
                temperatures.map { it.temp_min }.min()?.toInt(),
                temperatures.map { it.temp_max }.max()?.toInt()
        )
    }
}
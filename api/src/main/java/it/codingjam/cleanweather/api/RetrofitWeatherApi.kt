package it.codingjam.cleanweather.api

import it.codingjam.cleanweather.weather.Forecast
import it.codingjam.cleanweather.weather.TemperatureWrapper
import it.codingjam.cleanweather.weather.WeatherApi
import retrofit2.http.GET
import retrofit2.http.Query

const val OPEN_WEATHER_APP_ID = "90e68d358063403c485caacb28cd5727"

interface WeatherApiSpec {
    @GET("weather?appid=$OPEN_WEATHER_APP_ID&units=metric")
    suspend fun currentWeather(@Query("lat") lat: Double, @Query("lon") lon: Double): TemperatureWrapper

    @GET("forecast?appid=$OPEN_WEATHER_APP_ID&units=metric")
    suspend fun forecast(@Query("lat") lat: Double, @Query("lon") lon: Double): Forecast
}

class RetrofitWeatherApi : WeatherApi {

    private val api = RetrofitFactory.createService<WeatherApiSpec>(
            "http://api.openweathermap.org/data/2.5/")

    override fun currentWeather(lat: Double, lon: Double) = api.currentWeather(lat, lon)

    override fun forecast(lat: Double, lon: Double) = api.forecast(lat, lon)
}
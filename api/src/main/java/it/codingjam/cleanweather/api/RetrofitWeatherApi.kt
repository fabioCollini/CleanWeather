package it.codingjam.cleanweather.api

import it.codingjam.cleanweather.weather.Forecast
import it.codingjam.cleanweather.weather.TemperatureWrapper
import it.codingjam.cleanweather.weather.WeatherApi
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

const val OPEN_WEATHER_APP_ID = "90e68d358063403c485caacb28cd5727"

interface WeatherApiSpec {
    @GET("weather?appid=$OPEN_WEATHER_APP_ID&units=metric")
    suspend fun currentWeather(@Query("lat") lat: Double, @Query("lon") lon: Double): TemperatureWrapper

    @GET("forecast?appid=$OPEN_WEATHER_APP_ID&units=metric")
    suspend fun forecast(@Query("lat") lat: Double, @Query("lon") lon: Double): Forecast
}

@ApiSingleton
class RetrofitWeatherApi @Inject constructor(
        private val api: WeatherApiSpec
) : WeatherApi {

    override suspend fun currentWeather(lat: Double, lon: Double) = api.currentWeather(lat, lon)

    override suspend fun forecast(lat: Double, lon: Double) = api.forecast(lat, lon)
}
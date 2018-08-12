package it.codingjam.cleanweather.api

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

data class WeatherResponse(val id: Int, val description: String)

data class Temperature(val temp: Float, val temp_min: Float, val temp_max: Float)

data class CurrentWeatherResponse(
        var id: Int,
        var name: String,
        var weather: List<WeatherResponse>,
        var main: Temperature
)

data class ForecastResponseItem(val dt: Long, val weather: List<WeatherResponse>, val main: Temperature)

data class ForecastResponse(val list: List<ForecastResponseItem>)

const val OPEN_WEATHER_APP_ID = "90e68d358063403c485caacb28cd5727"

interface WeatherApi {
    @GET("weather?appid=$OPEN_WEATHER_APP_ID&units=metric")
    fun currentWeather(@Query("id") id: Int): Deferred<CurrentWeatherResponse>

    @GET("forecast?appid=$OPEN_WEATHER_APP_ID&units=metric")
    fun forecast(@Query("id") id: Int): Deferred<ForecastResponse>
}

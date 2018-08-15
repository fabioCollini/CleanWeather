package it.codingjam.cleanweather.api

import it.codingjam.cleanweather.weather.Forecast
import it.codingjam.cleanweather.weather.TemperatureWrapper
import it.codingjam.cleanweather.weather.WeatherApi
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

const val OPEN_WEATHER_APP_ID = "90e68d358063403c485caacb28cd5727"

interface WeatherApiSpec {
    @GET("weather?appid=$OPEN_WEATHER_APP_ID&units=metric")
    fun currentWeather(@Query("id") id: Int): Deferred<TemperatureWrapper>

    @GET("forecast?appid=$OPEN_WEATHER_APP_ID&units=metric")
    fun forecast(@Query("id") id: Int): Deferred<Forecast>
}

class RetrofitWeatherApi: WeatherApi {

    private val api = RetrofitFactory.createService<WeatherApiSpec>(
            "http://api.openweathermap.org/data/2.5/")

    override fun currentWeather(id: Int) = api.currentWeather(id)

    override fun forecast(id: Int) = api.forecast(id)
}
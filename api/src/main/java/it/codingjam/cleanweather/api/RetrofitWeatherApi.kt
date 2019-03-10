package it.codingjam.cleanweather.api

import it.codingjam.cleanweather.weather.Forecast
import it.codingjam.cleanweather.weather.TemperatureWrapper
import it.codingjam.cleanweather.weather.WeatherApi
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

const val OPEN_WEATHER_APP_ID = "90e68d358063403c485caacb28cd5727"

interface WeatherApiSpec {
    @GET("weather?appid=$OPEN_WEATHER_APP_ID&units=metric")
    fun currentWeather(@Query("lat") lat: Double, @Query("lon") lon: Double): Deferred<TemperatureWrapper>

    @GET("forecast?appid=$OPEN_WEATHER_APP_ID&units=metric")
    fun forecast(@Query("lat") lat: Double, @Query("lon") lon: Double): Deferred<Forecast>
}

@ApiSingleton
class RetrofitWeatherApi @Inject constructor(
        private val api: WeatherApiSpec
) : WeatherApi {

    override fun currentWeather(lat: Double, lon: Double) = api.currentWeather(lat, lon)

    override fun forecast(lat: Double, lon: Double) = api.forecast(lat, lon)
}
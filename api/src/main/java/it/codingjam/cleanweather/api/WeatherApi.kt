package it.codingjam.cleanweather.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

data class TemperatureResponse(val temp: Float, val temp_min: Float, val temp_max: Float)

data class TemperatureWrapper(
        var main: TemperatureResponse
) {
    constructor(temp: Float, min: Float, max: Float) :
            this(TemperatureResponse(temp, min, max))
}

data class Forecast(val list: List<TemperatureWrapper>) {
    constructor(vararg items: TemperatureWrapper) : this(listOf(*items))
}

const val OPEN_WEATHER_APP_ID = "90e68d358063403c485caacb28cd5727"

interface WeatherApi {
    @GET("weather?appid=$OPEN_WEATHER_APP_ID&units=metric")
    fun currentWeather(@Query("lat") lat: Double, @Query("lon") lon: Double): Deferred<TemperatureWrapper>

    @GET("forecast?appid=$OPEN_WEATHER_APP_ID&units=metric")
    fun forecast(@Query("lat") lat: Double, @Query("lon") lon: Double): Deferred<Forecast>
}

package it.codingjam.cleanweather.weather

import kotlinx.coroutines.Deferred

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

interface WeatherApi {
    fun currentWeather(lat: Double, lon: Double): Deferred<TemperatureWrapper>

    fun forecast(lat: Double, lon: Double): Deferred<Forecast>
}

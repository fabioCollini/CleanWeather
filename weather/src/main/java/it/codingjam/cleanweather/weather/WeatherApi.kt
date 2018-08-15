package it.codingjam.cleanweather.weather

import kotlinx.coroutines.experimental.Deferred

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
    fun currentWeather(id: Int): Deferred<TemperatureWrapper>

    fun forecast(id: Int): Deferred<Forecast>
}

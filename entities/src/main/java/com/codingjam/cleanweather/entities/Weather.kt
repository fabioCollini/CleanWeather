package com.codingjam.cleanweather.entities

data class Weather(
        val description: String,
        val temperature: Int,
        val forecastMin: Int?,
        val forecastMax: Int?
) {
    override fun toString() = "${temperature}º min ${forecastMin}º max ${forecastMax}º"
}
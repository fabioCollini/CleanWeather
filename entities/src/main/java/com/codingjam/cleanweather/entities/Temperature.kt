package com.codingjam.cleanweather.entities

data class Temperature(
        val current: Int,
        val forecastMin: Int?,
        val forecastMax: Int?
) {
    override fun toString() = "${current}º min ${forecastMin}º max ${forecastMax}º"
}
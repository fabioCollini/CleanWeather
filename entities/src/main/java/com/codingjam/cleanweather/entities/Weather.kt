package com.codingjam.cleanweather.entities

data class Weather(
        val description: String,
        val currentTemperature: Int,
        val forecastMin: Int?,
        val forecastMax: Int?
)
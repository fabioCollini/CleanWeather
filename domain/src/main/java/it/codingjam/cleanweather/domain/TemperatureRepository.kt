package it.codingjam.cleanweather.domain

import com.codingjam.cleanweather.entities.Temperature

interface TemperatureRepository {
    suspend fun getTemperature(lat: Double, lon: Double): Temperature
}
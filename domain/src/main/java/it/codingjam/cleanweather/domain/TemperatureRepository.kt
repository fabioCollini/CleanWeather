package it.codingjam.cleanweather.domain

import com.codingjam.cleanweather.entities.Temperature

interface TemperatureRepository {
    suspend fun getTemperature(cityId: Int): Temperature
}
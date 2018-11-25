package it.codingjam.cleanweather.domain

import com.codingjam.cleanweather.entities.City
import com.codingjam.cleanweather.entities.Location

interface LocationManager {
    suspend fun getLastLocation(): Location

    suspend fun getCities(location: Location): List<City>
}
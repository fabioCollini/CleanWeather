package it.codingjam.cleanweather.city

import com.codingjam.cleanweather.entities.City

interface CityRetriever {
    fun findCity(name: String): List<City>
}
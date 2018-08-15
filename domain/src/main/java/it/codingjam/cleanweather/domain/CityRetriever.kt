package it.codingjam.cleanweather.domain

import com.codingjam.cleanweather.entities.City

interface CityRetriever {
    fun findCity(name: String): List<City>
}
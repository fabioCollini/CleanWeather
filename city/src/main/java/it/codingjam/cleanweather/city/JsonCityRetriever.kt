package it.codingjam.cleanweather.city

import com.codingjam.cleanweather.entities.City
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson


class CityJson(
        val id: Int,
        val name: String,
        val country: String
)

class JsonCityRetriever : CityRetriever {

    private val allCities by lazy {
        val text = javaClass.getResource("/cityList.json").readText()
        Gson().fromJson<List<CityJson>>(text)
    }

    override fun findCity(name: String): List<City> {
        return allCities
                .filter { it.name == name }
                .map { City(it.id, it.name, it.country) }
                .toList()
    }
}
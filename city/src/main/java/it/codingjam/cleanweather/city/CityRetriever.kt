package it.codingjam.cleanweather.city

import com.codingjam.cleanweather.entities.City
import com.squareup.moshi.Moshi


class CityJson(
        val id: Int,
        val name: String,
        val country: String,
        val stat: Stat
)

class Stat(
        val population: Int
)

class CityRetriever {
    private val moshi = Moshi.Builder().build().adapter(Array<CityJson>::class.java)

    fun findCity(name: String): List<City> {
        val text = CityRetriever::class.java.getResource("/cityList.json").readText()
        return moshi.fromJson(text)!!
                .filter { it.name == name }
                .map { City(it.id, it.name, it.country, it.stat.population) }
                .toList()
    }
}
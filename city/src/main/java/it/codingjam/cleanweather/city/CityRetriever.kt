package it.codingjam.cleanweather.city

import com.codingjam.cleanweather.entities.City
import com.squareup.moshi.Moshi


class CityJson(
        val id: Int,
        val name: String,
        val country: String
)

class CityRetriever {
    private val moshi = Moshi.Builder().build().adapter(Array<CityJson>::class.java)

    private val text by lazy {
        CityRetriever::class.java.getResource("/cityList.json").readText()
    }

    fun findCity(name: String): List<City> {
        return moshi.fromJson(text)!!
                .filter { it.name == name }
                .map { City(it.id, it.name, it.country) }
                .toList()
    }
}
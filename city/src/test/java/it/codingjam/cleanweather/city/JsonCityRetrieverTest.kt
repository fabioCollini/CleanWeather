package it.codingjam.cleanweather.city

import assertk.assert
import assertk.assertions.containsExactly
import com.codingjam.cleanweather.entities.City
import org.junit.Test

class JsonCityRetrieverTest {
    @Test fun loadCities() {
        val cities = JsonCityRetriever().findCity("Firenze")
        assert(cities).containsExactly(City(3176959, "Firenze", "IT"))
    }
}
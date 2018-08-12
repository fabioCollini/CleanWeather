package it.codingjam.cleanweather.city

import assertk.assert
import assertk.assertions.containsExactly
import com.codingjam.cleanweather.entities.City
import org.junit.Test

class CityRetrieverTest {
    @Test fun loadCities() {
        val cities = CityRetriever().findCity("Firenze")
        assert(cities).containsExactly(City(3176959, "Firenze", "IT"))
    }
}
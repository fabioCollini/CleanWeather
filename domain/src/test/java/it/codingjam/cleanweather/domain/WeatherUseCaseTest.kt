package it.codingjam.cleanweather.domain

import assertk.assert
import assertk.assertions.isEqualTo
import com.codingjam.cleanweather.entities.City
import com.codingjam.cleanweather.entities.Temperature
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import it.codingjam.cleanweather.city.CityRetriever
import it.codingjam.cleanweather.weather.TemperatureRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

private const val CITY_ID = 123

class WeatherUseCaseTest {

    val cityRetriever: CityRetriever = mockk()

    val repository: TemperatureRepository = mockk()

    val useCase = WeatherUseCase(cityRetriever, repository)

    @Test
    fun retrieveCityData() {
        every { cityRetriever.findCity("Firenze") } returns
                listOf(City(CITY_ID, "Firenze", "IT"))
        coEvery { repository.getTemperature(CITY_ID) } returns
                Temperature(10, 8, 20)

        val cityData = runBlocking { useCase.getCityData("Firenze") }

        assert(cityData).isEqualTo("Firenze (IT) - 10º min 8º max 20º")
    }
}
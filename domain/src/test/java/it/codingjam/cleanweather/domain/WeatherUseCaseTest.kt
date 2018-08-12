package it.codingjam.cleanweather.domain

import assertk.assert
import assertk.assertions.isEqualTo
import com.codingjam.cleanweather.entities.City
import com.codingjam.cleanweather.entities.Weather
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import it.codingjam.cleanweather.city.CityRetriever
import it.codingjam.cleanweather.weather.WeatherRepository
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

private const val CITY_ID = 123

class WeatherUseCaseTest {

    val cityRetriever: CityRetriever = mockk()

    val weatherRepository: WeatherRepository = mockk()

    val useCase = WeatherUseCase(cityRetriever, weatherRepository)

    @Test
    fun retrieveCityData() {
        every { cityRetriever.findCity("Firenze") } returns
                listOf(City(CITY_ID, "Firenze", "Italy", 1234))
        coEvery { weatherRepository.getWeather(CITY_ID) } returns
                Weather("sun", 10, 8, 20)

        val cityData = runBlocking { useCase.getCityData("Firenze") }

        assert(cityData).isEqualTo(
                """Firenze - Italy
                    |10º min 8º max 20º
                """.trimMargin()
        )
    }
}
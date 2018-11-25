package it.codingjam.cleanweather.domain

import assertk.assert
import assertk.assertions.isEqualTo
import com.codingjam.cleanweather.entities.City
import com.codingjam.cleanweather.entities.Location
import com.codingjam.cleanweather.entities.Temperature
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

private const val LAT = 123.0
private const val LON = 456.0
private val LOCATION = Location(LAT, LON)

class WeatherUseCaseTest {

    val locationManager: LocationManager = mockk()

    val repository: TemperatureRepository = mockk()

    val useCase = WeatherUseCase(locationManager, repository)

    @Test
    fun retrieveCityData() {
        coEvery { locationManager.getLastLocation() } returns LOCATION
        coEvery { locationManager.getCities(LOCATION) } returns
                listOf(City("Firenze", "IT"))
        coEvery { repository.getTemperature(LAT, LON) } returns
                Temperature(10, 8, 20)

        val cityData = runBlocking { useCase.getCityData() }

        assert(cityData).isEqualTo("Firenze (IT) \n 10º min 8º max 20º")
    }
}
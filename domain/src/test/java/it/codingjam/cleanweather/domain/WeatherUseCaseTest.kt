package it.codingjam.cleanweather.domain

import assertk.assert
import assertk.assertions.isEqualTo
import com.codingjam.cleanweather.entities.City
import com.codingjam.cleanweather.entities.Location
import com.codingjam.cleanweather.entities.Temperature
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test

private const val LAT = 123.0
private const val LON = 456.0
private val LOCATION = Location(LAT, LON)

class WeatherUseCaseTest {

    private val locationManager: LocationManager = mock()

    private val repository: TemperatureRepository = mock()

    private val useCase = WeatherUseCase(locationManager, repository)

    @Test
    fun retrieveCityData() = runBlocking {
        whenever(locationManager.getLastLocation()) doReturn LOCATION
        whenever(locationManager.getCities(LOCATION)) doReturn
                listOf(City("Firenze", "IT"))
        whenever(repository.getTemperature(LAT, LON)) doReturn
                Temperature(10, 8, 20)

        val cityData = useCase.getCityData()

        assert(cityData).isEqualTo("Firenze (IT) \n 10º min 8º max 20º")
    }
}
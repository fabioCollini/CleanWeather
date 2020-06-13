package it.codingjam.cleanweather.weather

import assertk.assert
import assertk.assertions.isEqualTo
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test

private const val LAT = 123.0
private const val LON = 456.0

class OpenWeatherTemperatureRepositoryTest {

    private val api: WeatherApi = mock()

    private val repository = OpenWeatherTemperatureRepository(api)

    @Test
    fun calculateTemperatureAfterDownloadWeatherAndForecast() = runBlocking {
        whenever(api.currentWeather(LAT, LON)) doReturn
                TemperatureWrapper(20f, 6f, 25f)

        whenever(api.forecast(LAT, LON)) doReturn
                Forecast(
                        TemperatureWrapper(10f, 5f, 15f),
                        TemperatureWrapper(10f, 16f, 26f),
                        TemperatureWrapper(10f, 7f, 35f)
                )

        val temperature = repository.getTemperature(LAT, LON)

        assert(temperature.current).isEqualTo(20)
        assert(temperature.forecastMin).isEqualTo(5)
        assert(temperature.forecastMax).isEqualTo(35)
    }
}
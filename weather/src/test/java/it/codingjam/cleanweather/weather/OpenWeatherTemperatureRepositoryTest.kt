package it.codingjam.cleanweather.weather

import assertk.assert
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import it.codingjam.cleanweather.api.Forecast
import it.codingjam.cleanweather.api.TemperatureWrapper
import it.codingjam.cleanweather.api.WeatherApi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Test

private const val LAT = 123.0
private const val LON = 456.0

class OpenWeatherTemperatureRepositoryTest {

    private val api: WeatherApi = mockk()

    private val repository = OpenWeatherTemperatureRepository(api)

    @Test
    fun calculateTemperatureAfterDownloadWeatherAndForecast() = runBlocking {
        every { api.currentWeather(LAT, LON) } returns async {
            TemperatureWrapper(20f, 6f, 25f)
        }

        every { api.forecast(LAT, LON) } returns async {
            Forecast(
                    TemperatureWrapper(10f, 5f, 15f),
                    TemperatureWrapper(10f, 16f, 26f),
                    TemperatureWrapper(10f, 7f, 35f)
            )
        }

        val temperature = repository.getTemperature(LAT, LON)

        assert(temperature.current).isEqualTo(20)
        assert(temperature.forecastMin).isEqualTo(5)
        assert(temperature.forecastMax).isEqualTo(35)
    }
}
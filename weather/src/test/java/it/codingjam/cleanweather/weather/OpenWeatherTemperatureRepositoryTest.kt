package it.codingjam.cleanweather.weather

import assertk.assert
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import it.codingjam.cleanweather.api.Forecast
import it.codingjam.cleanweather.api.TemperatureWrapper
import it.codingjam.cleanweather.api.WeatherApi
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

private const val CITY_ID = 123

class OpenWeatherTemperatureRepositoryTest {

    private val api: WeatherApi = mockk()

    private val repository = OpenWeatherTemperatureRepository(api)

    @Test
    fun calculateTemperatureAfterDownloadWeatherAndForecast() {
        every { api.currentWeather(CITY_ID) } returns async {
            TemperatureWrapper(20f, 6f, 25f)
        }

        every { api.forecast(CITY_ID) } returns async {
            Forecast(
                    TemperatureWrapper(10f, 5f, 15f),
                    TemperatureWrapper(10f, 16f, 26f),
                    TemperatureWrapper(10f, 7f, 35f)
            )
        }

        val temperature = runBlocking { repository.getTemperature(CITY_ID) }

        assert(temperature.current).isEqualTo(20)
        assert(temperature.forecastMin).isEqualTo(5)
        assert(temperature.forecastMax).isEqualTo(35)
    }
}
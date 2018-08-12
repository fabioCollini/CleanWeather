package it.codingjam.cleanweather.weather

import assertk.assert
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import it.codingjam.cleanweather.api.*
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

private const val CITY_ID = 123

class WeatherRepositoryTest {

    private val api: WeatherApi = mockk()

    private val repository = WeatherRepository(api)

    @Test
    fun downloadWeatherAndForecast() {
        every { api.currentWeather(CITY_ID) } returns async {
            CurrentWeatherResponse(CITY_ID, "Florence", listOf(WeatherResponse(CITY_ID, "sun")), Temperature(20f, 5f, 25f))
        }

        every { api.forecast(CITY_ID) } returns async {
            ForecastResponse(listOf(
                    ForecastResponseItem(1L, emptyList(), Temperature(10f, 5f, 15f)),
                    ForecastResponseItem(2L, emptyList(), Temperature(10f, 16f, 26f)),
                    ForecastResponseItem(3L, emptyList(), Temperature(10f, 7f, 35f))
            ))
        }

        val weather = runBlocking { repository.getWeather(CITY_ID) }

        assert(weather.temperature).isEqualTo(20)
        assert(weather.forecastMin).isEqualTo(5)
        assert(weather.forecastMax).isEqualTo(35)
    }
}
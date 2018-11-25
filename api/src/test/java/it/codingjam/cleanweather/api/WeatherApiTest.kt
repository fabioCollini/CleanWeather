package it.codingjam.cleanweather.api

import assertk.assert
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

private const val LAT = 123.0
private const val LON = 456.0

class WeatherApiTest {
    @get:Rule
    val mockWebServer = MockWebServerRule()

    private var api: WeatherApi = RetrofitFactory.createService(mockWebServer.url)

    @Test
    fun shouldDownloadCurrentWeather() {
        mockWebServer.enqueueResponse("weather.json")

        val response = runBlocking {
            api.currentWeather(LAT, LON).await()
        }

        assert(response.main.temp).isEqualTo(303.15f)
        assert(mockWebServer.takeRequest().path).isEqualTo(
                "/weather?appid=$OPEN_WEATHER_APP_ID&units=metric&lat=$LAT&lon=$LON")
    }

    @Test
    fun shouldDownloadForecast() {
        mockWebServer.enqueueResponse("forecast.json")

        val response = runBlocking {
            api.forecast(LAT, LON).await()
        }

        assert(response.list).hasSize(40)
        assert(response.list[0].main.temp).isEqualTo(308.52f)
        assert(mockWebServer.takeRequest().path).isEqualTo(
                "/forecast?appid=$OPEN_WEATHER_APP_ID&units=metric&lat=$LAT&lon=$LON")
    }
}
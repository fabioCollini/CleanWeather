package it.codingjam.cleanweather.main

import it.codingjam.cleanweather.api.RetrofitFactory
import it.codingjam.cleanweather.api.WeatherApi
import it.codingjam.cleanweather.city.CityRetriever
import it.codingjam.cleanweather.domain.WeatherUseCase
import it.codingjam.cleanweather.weather.TemperatureRepository
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {
    val api = RetrofitFactory.createService<WeatherApi>(
            "http://api.openweathermap.org/data/2.5/")
    val weatherRepository = TemperatureRepository(api)
    val cityRetriever = CityRetriever()
    val useCase = WeatherUseCase(cityRetriever, weatherRepository)

    runBlocking {
        val result = useCase.getCityData(args[0])
        println(result)
    }
}
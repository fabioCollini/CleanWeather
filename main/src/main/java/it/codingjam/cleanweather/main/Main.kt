package it.codingjam.cleanweather.main

import it.codingjam.cleanweather.api.RetrofitFactory
import it.codingjam.cleanweather.api.RetrofitWeatherApi
import it.codingjam.cleanweather.api.WeatherApi
import it.codingjam.cleanweather.city.JsonCityRetriever
import it.codingjam.cleanweather.domain.WeatherUseCase
import it.codingjam.cleanweather.weather.OpenWeatherTemperatureRepository

suspend fun main(args: Array<String>) {
    val api = RetrofitFactory.createService<WeatherApi>()
    val weatherRepository = OpenWeatherTemperatureRepository(api)
    val cityRetriever = JsonCityRetriever()
    val useCase = WeatherUseCase(cityRetriever, weatherRepository)

    val result = useCase.getCityData(args[0])
    println(result)
}
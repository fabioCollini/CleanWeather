package it.codingjam.cleanweather.api

import inversion.InversionProvider
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate
import it.codingjam.cleanweather.weather.WeatherApi
import it.codingjam.cleanweather.weather.WeatherDependencies

interface ApiComponent : WeatherDependencies {
    override val weatherApi: WeatherApi
}

private class ApiComponentImpl : ApiComponent {
    private val weatherApiSpec by lazy {
        RetrofitFactory.createService<WeatherApiSpec>("https://api.openweathermap.org/data/2.5/")
    }

    override val weatherApi: WeatherApi by lazy {
        RetrofitWeatherApi(weatherApiSpec)
    }
}

fun ComponentHolder.apiComponent(): ApiComponent = getOrCreate { ApiComponentImpl() }

@InversionProvider
fun provideImpl(componentHolder: ComponentHolder): WeatherDependencies = componentHolder.apiComponent()

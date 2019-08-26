package it.codingjam.cleanweather.weather

import inversion.Inversion
import inversion.InversionDef
import inversion.of
import it.codingjam.cleanweather.domain.TemperatureRepository
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate

interface WeatherComponent {
    val temperatureRepository: TemperatureRepository
}

class WeatherComponentImpl(weatherDependencies: WeatherDependencies) : WeatherComponent, WeatherDependencies by weatherDependencies {
    override val temperatureRepository by lazy {
        OpenWeatherTemperatureRepository(weatherApi)
    }
}

interface WeatherDependencies {
    val weatherApi: WeatherApi
}

@get:InversionDef
val ComponentHolder.weatherDependencies by Inversion.of(WeatherDependencies::class)

fun ComponentHolder.weatherComponent(): WeatherComponent = getOrCreate {
    WeatherComponentImpl(weatherDependencies())
}
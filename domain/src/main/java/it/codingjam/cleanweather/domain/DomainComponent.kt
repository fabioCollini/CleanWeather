package it.codingjam.cleanweather.domain

import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import javax.inject.Singleton

typealias DomainSingleton = Singleton

interface DomainComponent {
    val weatherUseCase: WeatherUseCase
}

interface DomainDependencies {
    val locationManager: LocationManager

    val temperatureRepository: TemperatureRepository
}

fun ComponentHolder.domainComponent(): DomainComponent = castComponent()
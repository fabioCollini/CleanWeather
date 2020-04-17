package it.codingjam.cleanweather.domain

import inversion.Inversion
import inversion.InversionDef
import inversion.of
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

@get:InversionDef
val ComponentHolder.domainDependencies by Inversion.of(DomainDependencies::class)

fun ComponentHolder.domainComponent(): DomainComponent = castComponent()
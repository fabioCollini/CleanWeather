package it.codingjam.cleanweather.domain

import inversion.Inversion
import inversion.InversionDef
import inversion.of
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate

interface DomainComponent {
    val weatherUseCase: WeatherUseCase
}

class DomainComponentImpl(dependencies: DomainDependencies) : DomainComponent, DomainDependencies by dependencies {
    override val weatherUseCase by lazy {
        WeatherUseCase(locationManager, temperatureRepository)
    }
}

interface DomainDependencies {
    val locationManager: LocationManager

    val temperatureRepository: TemperatureRepository
}

@get:InversionDef
val ComponentHolder.domainDependencies by Inversion.of(DomainDependencies::class)

val ComponentHolder.domainComponent: DomainComponent
    get() = getOrCreate {
        DomainComponentImpl(domainDependencies())
    }
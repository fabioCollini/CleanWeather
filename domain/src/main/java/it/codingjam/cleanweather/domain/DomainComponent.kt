package it.codingjam.cleanweather.domain

import dagger.Component

interface DomainComponentProvider {
    val domainComponent: DomainComponent
}

@DomainSingleton
@Component(dependencies = [DomainDependencies::class])
interface DomainComponent {
    val weatherUseCase: WeatherUseCase
}

interface DomainDependencies {
    val locationManager: LocationManager

    val temperatureRepository: TemperatureRepository
}
package it.codingjam.cleanweather.domain

import dagger.Component

@DomainSingleton
@Component(dependencies = [DomainDependencies::class])
interface DomainComponent {
    val weatherUseCase: WeatherUseCase
}

interface DomainDependencies {
    val locationManager: LocationManager

    val temperatureRepository: TemperatureRepository
}
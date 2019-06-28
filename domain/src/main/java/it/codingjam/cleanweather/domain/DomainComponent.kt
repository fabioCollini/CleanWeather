package it.codingjam.cleanweather.domain

import dagger.Component
import javax.inject.Scope

@Scope
internal annotation class DomainSingleton

@DomainSingleton
@Component(dependencies = [DomainDependencies::class])
interface DomainComponent {
    val weatherUseCase: WeatherUseCase

    @Component.Factory
    interface Factory {
        fun create(domainDependencies: DomainDependencies): DomainComponent
    }
}

interface DomainDependencies {
    val locationManager: LocationManager

    val temperatureRepository: TemperatureRepository
}

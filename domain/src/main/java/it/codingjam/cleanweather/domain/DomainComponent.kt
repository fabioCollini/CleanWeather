package it.codingjam.cleanweather.domain

import dagger.Component
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.get
import it.codingjam.cleanweather.kotlinutils.getOrCreate
import javax.inject.Scope

@Scope
internal annotation class DomainSingleton

@DomainSingleton
@Component(dependencies = [DomainDependencies::class])
interface DomainComponent {
    val weatherUseCase: WeatherUseCase
}

interface DomainDependencies {
    val locationManager: LocationManager

    val temperatureRepository: TemperatureRepository
}

val ComponentHolder.domainComponent: DomainComponent
    get() = getOrCreate {
        DaggerDomainComponent.builder().domainDependencies(get()).build()
    }
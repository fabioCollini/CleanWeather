package it.codingjam.cleanweather.domain

import dagger.Component
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate
import it.codingjam.cleanweather.kotlinutils.loadSingleService
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

interface DomainDependenciesProvider {
    fun domainDependencies(componentHolder: ComponentHolder): DomainDependencies
}

val ComponentHolder.domainComponent: DomainComponent
    get() = getOrCreate {
        val provider = loadSingleService<DomainDependenciesProvider>()
        val domainDependencies = provider.domainDependencies(this)
        DaggerDomainComponent.factory().create(domainDependencies)
    }
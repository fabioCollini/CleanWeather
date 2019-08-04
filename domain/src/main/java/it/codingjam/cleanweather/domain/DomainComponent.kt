package it.codingjam.cleanweather.domain

import inversion.Inversion
import inversion.InversionDef
import inversion.of
import dagger.Component
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate
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

@get:InversionDef
val ComponentHolder.domainDependencies by Inversion.of(DomainDependencies::class)

val ComponentHolder.domainComponent: DomainComponent
    get() = getOrCreate {
        DaggerDomainComponent.factory().create(domainDependencies())
    }
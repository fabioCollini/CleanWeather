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

interface DomainComponent {
    val weatherUseCase: WeatherUseCase
}

@DomainSingleton
@Component(dependencies = [DomainDependencies::class])
interface DomainComponentImpl: DomainComponent {
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

fun ComponentHolder.domainComponent(): DomainComponent = getOrCreate {
    DaggerDomainComponentImpl.factory().create(domainDependencies())
}
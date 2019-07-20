package it.codingjam.cleanweather.domain

import com.nytimes.inversion.Inversion
import com.nytimes.inversion.InversionDef
import com.nytimes.inversion.of
import dagger.Component
import dagger.Module
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
package it.codingjam.cleanweather.app

import android.app.Application
import com.google.auto.service.AutoService
import it.codingjam.cleanweather.domain.DomainDependencies
import it.codingjam.cleanweather.domain.DomainDependenciesProvider
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.ComponentsMap
import it.codingjam.cleanweather.position.locationComponent
import it.codingjam.cleanweather.weather.weatherComponent

class WeatherApp : Application(), ComponentHolder by ComponentsMap()

@AutoService(DomainDependenciesProvider::class)
class DomainDependenciesProviderImpl : DomainDependenciesProvider {
    override fun domainDependencies(componentHolder: ComponentHolder): DomainDependencies =
            DaggerDomainDependenciesImpl.builder()
                    .locationComponent((componentHolder as Application).locationComponent)
                    .weatherComponent(componentHolder.weatherComponent)
                    .build()
}

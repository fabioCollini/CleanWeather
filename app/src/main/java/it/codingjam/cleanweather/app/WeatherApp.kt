package it.codingjam.cleanweather.app

import android.app.Application
import it.codingjam.cleanweather.api.DaggerApiComponent
import it.codingjam.cleanweather.domain.DaggerDomainComponent
import it.codingjam.cleanweather.domain.DomainComponentProvider
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.main.MainDependenciesProvider
import it.codingjam.cleanweather.position.DaggerLocationComponent
import it.codingjam.cleanweather.utils.ComponentHolder
import it.codingjam.cleanweather.utils.ComponentsMap
import it.codingjam.cleanweather.weather.DaggerWeatherComponent

class WeatherApp(
        private val componentsMap: ComponentsMap = ComponentsMap()
) : Application(), DomainComponentProvider, MainDependenciesProvider, ComponentHolder by componentsMap {

    val locationComponent = DaggerLocationComponent.builder().app(this).build()

    val apiComponent = DaggerApiComponent.builder().build()

    val weatherComponent = DaggerWeatherComponent
            .builder()
            .weatherDependencies(apiComponent)
            .build()

    override val domainComponent =
            DaggerDomainComponent.builder().domainDependencies(DomainDependenciesImpl(
                    locationComponent,
                    weatherComponent
            )).build()

    override val mainDependencies: MainDependencies
        get() = DaggerMainDependenciesImpl.create()
}

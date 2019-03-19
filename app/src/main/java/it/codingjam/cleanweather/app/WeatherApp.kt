package it.codingjam.cleanweather.app

import android.app.Application
import it.codingjam.cleanweather.api.DaggerApiComponent
import it.codingjam.cleanweather.domain.DaggerDomainComponent
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.position.DaggerLocationComponent
import it.codingjam.cleanweather.utils.ComponentHolder
import it.codingjam.cleanweather.utils.ComponentsMap
import it.codingjam.cleanweather.weather.DaggerWeatherComponent

class WeatherApp(
        private val componentsMap: ComponentsMap = ComponentsMap()
) : Application(), ComponentHolder by componentsMap {

    init {
        componentsMap.createComponent<MainDependencies> {
            DaggerMainDependenciesImpl.create()
        }

        componentsMap.createComponent<DomainComponent> {
            val locationComponent = DaggerLocationComponent.builder().app(this).build()

            val apiComponent = DaggerApiComponent.create()

            val weatherComponent = DaggerWeatherComponent
                    .builder()
                    .weatherDependencies(apiComponent)
                    .build()

            val domainDependencies = DomainDependenciesImpl(
                    locationComponent,
                    weatherComponent
            )
            DaggerDomainComponent.builder()
                    .domainDependencies(domainDependencies)
                    .build()
        }
    }
}

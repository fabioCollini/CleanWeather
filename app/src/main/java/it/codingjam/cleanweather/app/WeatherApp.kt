package it.codingjam.cleanweather.app

import android.app.Application
import it.codingjam.cleanweather.api.DaggerApiComponent
import it.codingjam.cleanweather.domain.DaggerDomainComponent
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.position.DaggerLocationComponentImpl
import it.codingjam.cleanweather.utils.ComponentHolder
import it.codingjam.cleanweather.utils.ComponentsMap
import it.codingjam.cleanweather.weather.DaggerWeatherComponentImpl

class WeatherApp(
        private val componentsMap: ComponentsMap = ComponentsMap()
) : Application(), ComponentHolder by componentsMap {

    init {
        componentsMap.createComponent<MainDependencies> {
            DaggerMainDependenciesImpl.create()
        }

        componentsMap.createComponent<DomainComponent> {
            val locationComponent = DaggerLocationComponentImpl.builder().app(this).build()

            val apiComponent = DaggerApiComponent.create()

            val weatherComponent = DaggerWeatherComponentImpl
                    .builder()
                    .weatherDependencies(apiComponent)
                    .build()

            val domainDependencies = DaggerDomainDependenciesImpl.builder()
                    .locationComponent(locationComponent)
                    .weatherComponent(weatherComponent)
                    .build()

            DaggerDomainComponent.builder()
                    .domainDependencies(domainDependencies)
                    .build()
        }
    }
}

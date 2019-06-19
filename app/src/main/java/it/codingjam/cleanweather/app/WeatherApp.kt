package it.codingjam.cleanweather.app

import android.app.Application
import it.codingjam.cleanweather.api.DaggerApiComponent
import it.codingjam.cleanweather.domain.DomainDependencies
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.ComponentsMap
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.position.DaggerLocationComponentImpl
import it.codingjam.cleanweather.weather.DaggerWeatherComponentImpl

class WeatherApp(
        private val componentsMap: ComponentsMap = ComponentsMap()
) : Application(), ComponentHolder by componentsMap {

    init {
        componentsMap.createComponent<MainDependencies> {
            DaggerMainDependenciesImpl.create()
        }

        componentsMap.createComponent<DomainDependencies> {
            val locationComponent = DaggerLocationComponentImpl.builder().app(this).build()

            val apiComponent = DaggerApiComponent.create()

            val weatherComponent = DaggerWeatherComponentImpl
                    .builder()
                    .weatherDependencies(apiComponent)
                    .build()

            DaggerDomainDependenciesImpl.builder()
                    .locationComponent(locationComponent)
                    .weatherComponent(weatherComponent)
                    .build()
        }
    }
}

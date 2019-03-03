package it.codingjam.cleanweather.app

import android.app.Application
import it.codingjam.cleanweather.domain.DomainComponentProvider
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.main.MainDependenciesProvider

class WeatherApp : Application(), DomainComponentProvider, MainDependenciesProvider {
    override val domainComponent =
            DaggerAppComponent.builder().app(this).build()

    override val mainDependencies: MainDependencies
        get() = domainComponent
}

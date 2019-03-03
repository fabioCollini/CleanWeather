package it.codingjam.cleanweather.mainapp

import android.app.Application
import it.codingjam.cleanweather.domain.DomainComponentProvider
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.main.MainDependenciesProvider

class MainApp : Application(), DomainComponentProvider, MainDependenciesProvider {
    override val domainComponent
        get() = DaggerAppComponent.builder().app(this).build()

    override val mainDependencies: MainDependencies
        get() = domainComponent
}
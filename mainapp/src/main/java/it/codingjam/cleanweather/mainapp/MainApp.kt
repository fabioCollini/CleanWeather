package it.codingjam.cleanweather.mainapp

import android.app.Application
import it.codingjam.cleanweather.domain.DaggerDomainComponent
import it.codingjam.cleanweather.domain.DomainComponentProvider
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.main.MainDependenciesProvider
import it.codingjam.cleanweather.utils.ComponentHolder
import it.codingjam.cleanweather.utils.ComponentsMap

class MainApp(
        private val componentsMap: ComponentsMap = ComponentsMap()
) : Application(), DomainComponentProvider, MainDependenciesProvider, ComponentHolder by componentsMap {

//    val locationComponent = DaggerLocationComponent.builder().app(this).build()
//
//    val apiComponent = DaggerApiComponent.builder().build()
//
//    val weatherComponent = DaggerWeatherComponent
//            .builder()
//            .weatherDependencies(apiComponent)
//            .build()

    override val domainComponent =
            DaggerDomainComponent.builder().domainDependencies(FakeDomainDependencies()).build()

    override val mainDependencies: MainDependencies
        get() = FakeMainDependencies()
}
package it.codingjam.cleanweather.mainapp

import android.app.Application
import it.codingjam.cleanweather.domain.DaggerDomainComponent
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.utils.ComponentHolder
import it.codingjam.cleanweather.utils.ComponentsMap
import it.codingjam.cleanweather.utils.getOrCreate

class MainApp(
        private val componentsMap: ComponentsMap = ComponentsMap()
) : Application(), ComponentHolder by componentsMap {

    init {
        getOrCreate<MainDependencies> {
            FakeMainDependencies()
        }

        getOrCreate {
            DaggerDomainComponent.builder()
                    .domainDependencies(FakeDomainDependencies())
                    .build()
        }
    }
}
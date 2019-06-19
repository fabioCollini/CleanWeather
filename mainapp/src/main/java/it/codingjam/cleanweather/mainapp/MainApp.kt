package it.codingjam.cleanweather.mainapp

import android.app.Application
import it.codingjam.cleanweather.domain.DaggerDomainComponent
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.ComponentsMap
import it.codingjam.cleanweather.kotlinutils.getOrCreate
import it.codingjam.cleanweather.main.MainDependencies

class MainApp : Application(), ComponentHolder by ComponentsMap() {

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
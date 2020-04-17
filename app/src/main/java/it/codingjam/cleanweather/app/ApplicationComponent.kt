package it.codingjam.cleanweather.app

import dagger.Component
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.domain.DomainDependencies
import it.codingjam.cleanweather.main.MainComponent
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            MainDependenciesModule::class
        ],
        dependencies = [
            DomainDependencies::class
        ]
)
interface ApplicationComponent : MainComponent, DomainDependencies {
    @Component.Factory
    interface Factory {
        fun create(
                domainDependencies: DomainDependencies
        ): ApplicationComponent
    }
}
package it.codingjam.cleanweather.app

import dagger.Component
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.main.MainComponent
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            MainDependenciesModule::class
        ],
        dependencies = [
            DomainComponent::class
        ]
)
interface ApplicationComponent : MainComponent {
    @Component.Factory
    interface Factory {
        fun create(domainComponent: DomainComponent): ApplicationComponent
    }
}
package it.codingjam.cleanweather.app

import dagger.Component
import it.codingjam.cleanweather.domain.DomainDependencies
import it.codingjam.cleanweather.main.MainComponent
import it.codingjam.cleanweather.position.LocationComponent
import it.codingjam.cleanweather.weather.WeatherComponent
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            MainDependenciesModule::class
        ],
        dependencies = [
            LocationComponent::class,
            WeatherComponent::class
        ]
)
interface ApplicationComponent : MainComponent, DomainDependencies {
    @Component.Factory
    interface Factory {
        fun create(
                locationComponent: LocationComponent,
                weatherComponent: WeatherComponent
        ): ApplicationComponent
    }
}
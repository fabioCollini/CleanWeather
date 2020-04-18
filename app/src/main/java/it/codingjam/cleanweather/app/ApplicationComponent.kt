package it.codingjam.cleanweather.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import it.codingjam.cleanweather.api.ApiModule
import it.codingjam.cleanweather.domain.DomainDependencies
import it.codingjam.cleanweather.main.MainComponent
import it.codingjam.cleanweather.position.LocationModule
import it.codingjam.cleanweather.weather.WeatherModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            MainDependenciesModule::class,
            LocationModule::class,
            WeatherModule::class,
            ApiModule::class
        ]
)
interface ApplicationComponent : MainComponent, DomainDependencies {
    @Component.Factory
    interface Factory {
        fun create(
                @BindsInstance app: Application
        ): ApplicationComponent
    }
}
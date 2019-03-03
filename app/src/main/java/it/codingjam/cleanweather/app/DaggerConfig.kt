package it.codingjam.cleanweather.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.api.ApiModule
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.main.MainNavigator
import it.codingjam.cleanweather.position.LocationModule
import it.codingjam.cleanweather.weather.WeatherModule
import javax.inject.Singleton

@Module
class MainDependenciesModule {
    @Provides
    @Singleton
    fun provideNavigator(): MainNavigator = MainNavigatorImpl()
}

@Component(modules = [
    MainDependenciesModule::class,
    ApiModule::class,
    LocationModule::class,
    WeatherModule::class
])
@Singleton
interface AppComponent : DomainComponent, MainDependencies {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun app(app: Application): Builder
    }
}
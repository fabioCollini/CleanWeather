package it.codingjam.cleanweather.main

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import it.codingjam.cleanweather.api.ApiModule
import it.codingjam.cleanweather.position.LocationModule
import it.codingjam.cleanweather.weather.WeatherModule
import javax.inject.Singleton

@Component(modules = [
    ApiModule::class,
    LocationModule::class,
    WeatherModule::class
])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun app(pp: Application): Builder
    }
}
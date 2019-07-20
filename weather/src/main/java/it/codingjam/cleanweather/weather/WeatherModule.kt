package it.codingjam.cleanweather.weather

import com.nytimes.inversion.Inversion
import com.nytimes.inversion.InversionDef
import com.nytimes.inversion.of
import dagger.Component
import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.domain.TemperatureRepository
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate
import javax.inject.Scope

@Scope
internal annotation class WeatherSingleton

interface WeatherComponent {
    val temperatureRepository: TemperatureRepository
}

@Component(
        modules = [WeatherModule::class],
        dependencies = [WeatherDependencies::class]
)
@WeatherSingleton
interface WeatherComponentImpl : WeatherComponent {

    @Component.Factory
    interface Factory {
        fun create(weatherDependencies: WeatherDependencies): WeatherComponent
    }
}

interface WeatherDependencies {
    val weatherApi: WeatherApi
}

@get:InversionDef
val ComponentHolder.weatherDependencies by Inversion.of(WeatherDependencies::class)

@Module
internal class WeatherModule {

    @WeatherSingleton
    @Provides
    fun provideTemperatureRepository(impl: OpenWeatherTemperatureRepository): TemperatureRepository = impl
}

val ComponentHolder.weatherComponent: WeatherComponent
    get() = getOrCreate {
        DaggerWeatherComponentImpl
                .factory()
                .create(weatherDependencies())
    }
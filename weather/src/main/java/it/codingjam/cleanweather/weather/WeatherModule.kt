package it.codingjam.cleanweather.weather

import dagger.Component
import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.domain.TemperatureRepository
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.DependenciesCreator
import it.codingjam.cleanweather.kotlinutils.getOrCreate
import it.codingjam.cleanweather.kotlinutils.loadSingleService
import java.util.*
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
internal interface WeatherComponentImpl : WeatherComponent

interface WeatherDependencies {
    val weatherApi: WeatherApi

    interface Creator : DependenciesCreator<WeatherDependencies>
}

@Module
internal class WeatherModule {

    @WeatherSingleton
    @Provides
    fun provideTemperatureRepository(impl: OpenWeatherTemperatureRepository): TemperatureRepository = impl
}

val ComponentHolder.weatherComponent: WeatherComponent
    get() = getOrCreate {
        DaggerWeatherComponentImpl
                .builder()
                .weatherDependencies(loadSingleService<WeatherDependencies.Creator>().dependencies(this))
                .build()
    }

inline fun <D, reified T : DependenciesCreator<D>> ComponentHolder.dependencies(): D {
    val creator = loadSingleService<T>()
    return creator.dependencies(this)
}
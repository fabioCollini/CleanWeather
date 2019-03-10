package it.codingjam.cleanweather.weather

import dagger.Component
import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.domain.TemperatureRepository
import javax.inject.Scope


@Scope
@Retention
annotation class WeatherSingleton

@Component(
        modules = [WeatherModule::class],
        dependencies = [WeatherDependencies::class]
)
@WeatherSingleton
interface WeatherComponent {
    val temperatureRepository: TemperatureRepository
}

interface WeatherDependencies {
    val weatherApi: WeatherApi
}

@Module
class WeatherModule {

    @WeatherSingleton
    @Provides
    fun provideTemperatureRepository(impl: OpenWeatherTemperatureRepository): TemperatureRepository = impl
}
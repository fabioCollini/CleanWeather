package it.codingjam.cleanweather.weather

import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.domain.TemperatureRepository
import javax.inject.Singleton

@Module
class WeatherModule {

    @Singleton
    @Provides
    fun provideTemperatureRepository(impl: OpenWeatherTemperatureRepository): TemperatureRepository = impl
}
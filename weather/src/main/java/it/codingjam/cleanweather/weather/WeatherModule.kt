package it.codingjam.cleanweather.weather

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.codingjam.cleanweather.domain.TemperatureRepository

@Module
@InstallIn(SingletonComponent::class)
interface WeatherModule {
    @Binds
    fun OpenWeatherTemperatureRepository.provideTemperatureRepository(): TemperatureRepository
}
package it.codingjam.cleanweather.app

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import it.codingjam.cleanweather.api.RetrofitFactory
import it.codingjam.cleanweather.api.RetrofitWeatherApi
import it.codingjam.cleanweather.api.WeatherApiSpec
import it.codingjam.cleanweather.domain.TemperatureRepository
import it.codingjam.cleanweather.main.MainNavigator
import it.codingjam.cleanweather.weather.OpenWeatherTemperatureRepository
import it.codingjam.cleanweather.weather.WeatherApi
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface MainDependenciesModule {
    @Binds
    fun MainNavigatorImpl.provideNavigator(): MainNavigator

    @Binds
    fun RetrofitWeatherApi.provideWeatherApi(): WeatherApi

    @Binds
    fun OpenWeatherTemperatureRepository.provideTemperatureRepository(): TemperatureRepository

    companion object {
        @Singleton
        @Provides
        fun provideWeatherApiSpec(): WeatherApiSpec =
                RetrofitFactory.createService("https://api.openweathermap.org/data/2.5/")
    }
}
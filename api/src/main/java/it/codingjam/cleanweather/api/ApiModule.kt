package it.codingjam.cleanweather.api

import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.weather.WeatherApi
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideWeatherApiSpec(): WeatherApiSpec =
            RetrofitFactory.createService("https://api.openweathermap.org/data/2.5/")

    @Singleton
    @Provides
    fun provideWeatherApi(impl: RetrofitWeatherApi): WeatherApi = impl
}
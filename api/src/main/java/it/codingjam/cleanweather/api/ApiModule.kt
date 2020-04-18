package it.codingjam.cleanweather.api

import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.weather.WeatherApi
import javax.inject.Singleton

typealias ApiSingleton = Singleton

@Module
class ApiModule {

    @ApiSingleton
    @Provides
    fun provideWeatherApiSpec(): WeatherApiSpec =
            RetrofitFactory.createService("https://api.openweathermap.org/data/2.5/")

    @ApiSingleton
    @Provides
    internal fun provideWeatherApi(impl: RetrofitWeatherApi): WeatherApi = impl
}

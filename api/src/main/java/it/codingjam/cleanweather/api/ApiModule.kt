package it.codingjam.cleanweather.api

import inversion.InversionProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate
import it.codingjam.cleanweather.weather.WeatherApi
import it.codingjam.cleanweather.weather.WeatherDependencies
import javax.inject.Scope

@Scope
internal annotation class ApiSingleton

@Component(modules = [ApiModule::class])
@ApiSingleton
interface ApiComponent : WeatherDependencies {
    override val weatherApi: WeatherApi
}

@Module
internal class ApiModule {

    @ApiSingleton
    @Provides
    fun provideWeatherApiSpec(): WeatherApiSpec =
            RetrofitFactory.createService("https://api.openweathermap.org/data/2.5/")

    @ApiSingleton
    @Provides
    fun provideWeatherApi(impl: RetrofitWeatherApi): WeatherApi = impl
}

val ComponentHolder.apiComponent: ApiComponent get() = getOrCreate { DaggerApiComponent.create() }

@InversionProvider
fun provideImpl(componentHolder: ComponentHolder): WeatherDependencies = componentHolder.apiComponent

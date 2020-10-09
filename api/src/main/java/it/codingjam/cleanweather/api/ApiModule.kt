package it.codingjam.cleanweather.api

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ElementsIntoSet
import it.codingjam.cleanweather.weather.WeatherApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface ApiModule {
    @Binds
    fun RetrofitWeatherApi.provideWeatherApi(): WeatherApi

    companion object {
        @Singleton
        @Provides
        fun provideWeatherApiSpec(client: OkHttpClient): WeatherApiSpec =
                RetrofitFactory.createService(client, "https://api.openweathermap.org/data/2.5/")

        @Provides
        @ElementsIntoSet
        fun provideDefaultInterceptors(): Set<Interceptor> = emptySet()

        @Singleton
        @Provides
        fun providesOkHttpClient(
            interceptors: @JvmSuppressWildcards Set<Interceptor>
        ): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            interceptors.forEach {
                httpClient.addInterceptor(it)
            }
            return httpClient.build()
        }
    }
}

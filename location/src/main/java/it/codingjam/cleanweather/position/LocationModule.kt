package it.codingjam.cleanweather.position

import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.domain.LocationManager
import javax.inject.Singleton

typealias LocationSingleton = Singleton

@Module
class LocationModule {

    @LocationSingleton
    @Provides
    fun provideLocationManager(impl: AndroidLocationManager): LocationManager = impl
}
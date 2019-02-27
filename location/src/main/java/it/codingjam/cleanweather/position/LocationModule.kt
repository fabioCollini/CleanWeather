package it.codingjam.cleanweather.position

import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.domain.LocationManager
import javax.inject.Singleton

@Module
class LocationModule {

    @Singleton
    @Provides
    fun provideLocationManager(impl: AndroidLocationManager): LocationManager = impl
}
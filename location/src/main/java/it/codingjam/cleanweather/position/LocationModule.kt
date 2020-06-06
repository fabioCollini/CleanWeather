package it.codingjam.cleanweather.position

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import it.codingjam.cleanweather.domain.LocationManager
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
internal class LocationModule {

    @Singleton
    @Provides
    fun provideLocationManager(impl: AndroidLocationManager): LocationManager = impl
}

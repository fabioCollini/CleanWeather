package it.codingjam.cleanweather.position

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.domain.LocationManager
import javax.inject.Scope

@Scope
@Retention
annotation class LocationSingleton

@Component(
        modules = [LocationModule::class]
)
@LocationSingleton
interface LocationComponent {
    val locationManager: LocationManager

    @Component.Builder
    interface Builder {
        fun build(): LocationComponent

        @BindsInstance
        fun app(app: Application): Builder
    }
}

@Module
class LocationModule {

    @LocationSingleton
    @Provides
    fun provideLocationManager(impl: AndroidLocationManager): LocationManager = impl
}
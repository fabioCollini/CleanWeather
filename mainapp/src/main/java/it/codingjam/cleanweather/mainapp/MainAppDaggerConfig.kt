package it.codingjam.cleanweather.mainapp

import android.app.Activity
import android.app.Application
import android.widget.Toast
import com.codingjam.cleanweather.entities.City
import com.codingjam.cleanweather.entities.Location
import com.codingjam.cleanweather.entities.Temperature
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.domain.LocationManager
import it.codingjam.cleanweather.domain.TemperatureRepository
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.main.MainNavigator
import javax.inject.Singleton

@Module
class MainDependenciesModule {
    @Provides
    @Singleton
    fun provideNavigator(): MainNavigator = object : MainNavigator {
        override fun openDetail(activity: Activity) {
            Toast.makeText(activity, "Layout clicked!", Toast.LENGTH_LONG).show()
        }
    }

    @Singleton
    @Provides
    fun provideLocationManager(): LocationManager = object : LocationManager {
        override suspend fun getLastLocation() = Location(10.1, 20.2)

        override suspend fun getCities(location: Location) = listOf(City("city", "country"))
    }

    @Singleton
    @Provides
    fun provideTemperatureRepository() = object : TemperatureRepository {
        override suspend fun getTemperature(lat: Double, lon: Double) = Temperature(1, 2, 3)

        override suspend fun getForecast(lat: Double, lon: Double) = listOf(
                Temperature(1, 2, 3),
                Temperature(4, 5, 6)
        )
    }
}

@Component(modules = [
    MainDependenciesModule::class
])
@Singleton
interface AppComponent : DomainComponent, MainDependencies {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun app(app: Application): Builder
    }
}
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
import it.codingjam.cleanweather.domain.DomainDependencies
import it.codingjam.cleanweather.domain.LocationManager
import it.codingjam.cleanweather.domain.TemperatureRepository
import it.codingjam.cleanweather.main.MainComponent
import it.codingjam.cleanweather.main.MainNavigator
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            MainFakeModule::class
        ]
)
interface MainApplicationComponent : MainComponent, DomainDependencies {
    @Component.Factory
    interface Factory {
        fun create(
                @BindsInstance app: Application
        ): MainApplicationComponent
    }
}

@Module
class MainFakeModule {
    @Singleton
    @Provides
    fun provideLocationManager() = object : LocationManager {
        override suspend fun getLastLocation() = Location(10.1, 20.2)

        override suspend fun getCities(location: Location) = listOf(City("city", "country"))
    }

    @Singleton
    @Provides
    fun provideTemperatureRepository(): TemperatureRepository = object : TemperatureRepository {
        override suspend fun getTemperature(lat: Double, lon: Double) = Temperature(1, 2, 3)

        override suspend fun getForecast(lat: Double, lon: Double) = listOf(
                Temperature(1, 2, 3),
                Temperature(4, 5, 6)
        )
    }

    @Singleton
    @Provides
    fun provideMainNavigator(): MainNavigator = object : MainNavigator {
        override fun openDetail(activity: Activity) {
            Toast.makeText(activity, "Layout clicked!", Toast.LENGTH_LONG).show()
        }
    }
}

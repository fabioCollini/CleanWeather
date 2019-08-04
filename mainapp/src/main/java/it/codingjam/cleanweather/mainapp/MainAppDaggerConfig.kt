package it.codingjam.cleanweather.mainapp

import android.app.Activity
import android.widget.Toast
import com.codingjam.cleanweather.entities.City
import com.codingjam.cleanweather.entities.Location
import com.codingjam.cleanweather.entities.Temperature
import inversion.InversionProvider
import it.codingjam.cleanweather.domain.DomainDependencies
import it.codingjam.cleanweather.domain.LocationManager
import it.codingjam.cleanweather.domain.TemperatureRepository
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.main.MainNavigator

class FakeDomainDependencies : DomainDependencies {
    override val locationManager: LocationManager
        get() = object : LocationManager {
            override suspend fun getLastLocation() = Location(10.1, 20.2)

            override suspend fun getCities(location: Location) = listOf(City("city", "country"))
        }
    override val temperatureRepository: TemperatureRepository
        get() = object : TemperatureRepository {
            override suspend fun getTemperature(lat: Double, lon: Double) = Temperature(1, 2, 3)

            override suspend fun getForecast(lat: Double, lon: Double) = listOf(
                    Temperature(1, 2, 3),
                    Temperature(4, 5, 6)
            )
        }
}

@InversionProvider
fun provideImpl(componentHolder: ComponentHolder): DomainDependencies = componentHolder.getOrCreate { FakeDomainDependencies() }

class FakeMainDependencies : MainDependencies {
    override val mainNavigator: MainNavigator
        get() = object : MainNavigator {
            override fun openDetail(activity: Activity) {
                Toast.makeText(activity, "Layout clicked!", Toast.LENGTH_LONG).show()
            }
        }
}

@InversionProvider
fun provideMainDependenciesImpl(componentHolder: ComponentHolder): MainDependencies = componentHolder.getOrCreate { FakeMainDependencies() }
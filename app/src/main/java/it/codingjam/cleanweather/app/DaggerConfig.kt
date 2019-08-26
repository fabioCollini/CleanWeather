package it.codingjam.cleanweather.app

import android.app.Application
import inversion.InversionProvider
import it.codingjam.cleanweather.domain.DomainDependencies
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.position.LocationComponent
import it.codingjam.cleanweather.position.locationComponent
import it.codingjam.cleanweather.utils.ComponentHolderApp
import it.codingjam.cleanweather.weather.WeatherComponent
import it.codingjam.cleanweather.weather.weatherComponent

private class MainDependenciesImpl : MainDependencies {
    override val mainNavigator by lazy { MainNavigatorImpl() }
}

@InversionProvider
fun ComponentHolder.provideMainDependenciesImpl(): MainDependencies = getOrCreate {
    MainDependenciesImpl()
}

private class DomainDependenciesImpl(
        locationComponent: LocationComponent,
        weatherComponent: WeatherComponent
) : DomainDependencies, LocationComponent by locationComponent, WeatherComponent by weatherComponent

@InversionProvider
fun ComponentHolder.provideImpl(): DomainDependencies = getOrCreate {
    DomainDependenciesImpl(
            (this as ComponentHolderApp).locationComponent(),
            weatherComponent()
    )
}
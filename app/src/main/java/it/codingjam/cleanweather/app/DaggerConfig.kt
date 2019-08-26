package it.codingjam.cleanweather.app

import android.app.Application
import dagger.Binds
import dagger.Component
import dagger.Module
import inversion.InversionProvider
import it.codingjam.cleanweather.domain.DomainDependencies
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.main.MainNavigator
import it.codingjam.cleanweather.position.LocationComponent
import it.codingjam.cleanweather.position.locationComponent
import it.codingjam.cleanweather.utils.ComponentHolderApp
import it.codingjam.cleanweather.weather.WeatherComponent
import it.codingjam.cleanweather.weather.weatherComponent
import javax.inject.Scope

@Scope
annotation class AppSingleton

@Module
interface MainDependenciesModule {
    @Binds
    fun provideNavigator(impl: MainNavigatorImpl): MainNavigator
}

@Component(
    modules = [
        MainDependenciesModule::class
    ]
)
@AppSingleton
interface MainDependenciesImpl : MainDependencies

@InversionProvider
fun ComponentHolder.provideMainDependenciesImpl(): MainDependencies = getOrCreate {
    DaggerMainDependenciesImpl.create()
}

@Component(dependencies = [LocationComponent::class, WeatherComponent::class])
interface DomainDependenciesImpl : DomainDependencies {

    @Component.Factory
    interface Factory {
        fun create(
            locationComponent: LocationComponent,
            weatherComponent: WeatherComponent
        ): DomainDependenciesImpl
    }
}

@InversionProvider
fun ComponentHolder.provideImpl(): DomainDependencies = getOrCreate {
    DaggerDomainDependenciesImpl.factory().create(
            (this as ComponentHolderApp).locationComponent(),
            weatherComponent()
    )
}
package it.codingjam.cleanweather.app

import com.google.auto.service.AutoService
import dagger.Binds
import dagger.Component
import dagger.Module
import it.codingjam.cleanweather.domain.DomainDependencies
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.main.MainDependencies
import it.codingjam.cleanweather.main.MainNavigator
import it.codingjam.cleanweather.position.LocationComponent
import it.codingjam.cleanweather.weather.WeatherComponent
import javax.inject.Scope

@Scope
annotation class AppSingleton

@Module
interface MainDependenciesModule {
    @Binds
    fun provideNavigator(impl: MainNavigatorImpl): MainNavigator
}

@Component(modules = [
    MainDependenciesModule::class
])
@AppSingleton
interface MainDependenciesImpl : MainDependencies

@Component(dependencies = [LocationComponent::class, WeatherComponent::class])
interface DomainDependenciesImpl : DomainDependencies

@AutoService(MainDependencies.Creator::class)
class MainDependenciesCreator : MainDependencies.Creator {
    override fun dependencies(componentHolder: ComponentHolder) = DaggerMainDependenciesImpl.create()
}
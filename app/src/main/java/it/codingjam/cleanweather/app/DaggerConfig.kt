package it.codingjam.cleanweather.app

import dagger.Binds
import dagger.Module
import it.codingjam.cleanweather.main.MainNavigator
import javax.inject.Singleton

typealias AppSingleton = Singleton

@Module
interface MainDependenciesModule {
    @Binds
    fun provideNavigator(impl: MainNavigatorImpl): MainNavigator
}
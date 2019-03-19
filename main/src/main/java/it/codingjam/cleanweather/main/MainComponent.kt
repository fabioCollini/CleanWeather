package it.codingjam.cleanweather.main

import android.app.Activity
import dagger.Component
import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.domain.FeatureSingleton

@Component(
        modules = [MainModule::class],
        dependencies = [
            DomainComponent::class,
            MainDependencies::class
        ])
@FeatureSingleton
interface MainComponent {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): MainComponent

        fun mainDependencies(dependencies: MainDependencies): Builder

        fun domainComponent(domainComponent: DomainComponent): Builder
    }
}

@Module
class MainModule {
    @Provides
    @FeatureSingleton
    fun providePermissionManager() = PermissionManager()
}

interface MainNavigator {
    fun openDetail(activity: Activity)
}

interface MainDependencies {
    val mainNavigator: MainNavigator
}
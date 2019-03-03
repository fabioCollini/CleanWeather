package it.codingjam.cleanweather.main

import android.app.Activity
import dagger.Component
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.domain.FeatureSingleton

@Component(dependencies = [
    DomainComponent::class,
    MainDependencies::class
])
@FeatureSingleton
interface MainComponent {
    fun inject(activity: MainActivity)
}

interface MainNavigator {
    fun openDetail(activity: Activity)
}

interface MainDependencies {
    val mainNavigator: MainNavigator
}

interface MainDependenciesProvider {
    val mainDependencies: MainDependencies
}
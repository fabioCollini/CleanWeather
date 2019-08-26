package it.codingjam.cleanweather.main

import android.app.Activity
import inversion.Inversion
import inversion.InversionDef
import inversion.of
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.domain.domainComponent
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate

interface MainComponent {
    val weatherViewModel: WeatherViewModel
    val mainNavigator: MainNavigator
    val permissionManager: PermissionManager
}

private class MainComponentImpl(
        domainComponent: DomainComponent,
        mainDependencies: MainDependencies
) : MainComponent, MainDependencies by mainDependencies, DomainComponent by domainComponent {
    override val weatherViewModel: WeatherViewModel
        get() = WeatherViewModel(weatherUseCase)

    override val permissionManager by lazy { PermissionManager() }
}

interface MainNavigator {
    fun openDetail(activity: Activity)
}

interface MainDependencies {
    val mainNavigator: MainNavigator
}

@get:InversionDef
val ComponentHolder.mainDependencies by Inversion.of(MainDependencies::class)

fun ComponentHolder.mainComponent(): MainComponent = getOrCreate {
    MainComponentImpl(domainComponent(), mainDependencies())
}
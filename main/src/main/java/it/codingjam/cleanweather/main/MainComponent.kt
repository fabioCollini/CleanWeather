package it.codingjam.cleanweather.main

import android.app.Activity
import dagger.BindsInstance
import dagger.Component
import inversion.Inversion
import inversion.InversionDef
import inversion.of
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.domain.FeatureSingleton
import it.codingjam.cleanweather.domain.domainComponent
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate

@Component(
        dependencies = [
            DomainComponent::class,
            MainDependencies::class
        ])
@FeatureSingleton
interface MainComponent {
    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
                dependencies: MainDependencies,
                domainComponent: DomainComponent,
                @BindsInstance
                permissionManager: PermissionManager
        ): MainComponent
    }
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
    println("onCreate DaggerMainComponent.factory()")
    DaggerMainComponent.factory()
            .create(
                    mainDependencies(),
                    domainComponent(),
                    PermissionManager()
            )
}
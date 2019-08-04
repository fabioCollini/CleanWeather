package it.codingjam.cleanweather.main

import android.app.Activity
import inversion.Inversion
import inversion.InversionDef
import inversion.of
import dagger.Component
import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.domain.FeatureSingleton
import it.codingjam.cleanweather.domain.domainComponent
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate

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

@get:InversionDef
val ComponentHolder.mainDependencies by Inversion.of(MainDependencies::class)

val ComponentHolder.mainComponent: MainComponent
    get() = getOrCreate {
        DaggerMainComponent.builder()
                .domainComponent(domainComponent)
                .mainDependencies(mainDependencies())
                .build()
    }
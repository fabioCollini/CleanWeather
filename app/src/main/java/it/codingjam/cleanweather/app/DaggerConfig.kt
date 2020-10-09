package it.codingjam.cleanweather.app

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import it.codingjam.cleanweather.main.MainNavigator

@Module
@InstallIn(ActivityComponent::class)
interface NavigatorModule {
    @Binds
    fun MainNavigatorImpl.provideNavigator(): MainNavigator
}

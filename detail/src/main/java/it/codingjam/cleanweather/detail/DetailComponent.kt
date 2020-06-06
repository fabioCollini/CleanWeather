package it.codingjam.cleanweather.detail

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface DetailComponent {
    fun inject(activity: DetailActivity)
}

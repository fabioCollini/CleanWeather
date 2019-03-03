package it.codingjam.cleanweather.detail

import dagger.Component
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.domain.FeatureSingleton

@Component(dependencies = [DomainComponent::class])
@FeatureSingleton
interface DetailComponent {
    fun inject(activity: DetailActivity)
}
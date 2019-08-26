package it.codingjam.cleanweather.detail

import it.codingjam.cleanweather.domain.DomainComponent

interface DetailComponent {
    val detailViewModel: DetailViewModel
}

class DetailComponentImpl(domainComponent: DomainComponent) : DetailComponent, DomainComponent by domainComponent {
    override val detailViewModel: DetailViewModel
        get() = DetailViewModel(weatherUseCase)
}
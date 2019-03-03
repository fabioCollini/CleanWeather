package it.codingjam.cleanweather.domain

interface DomainComponentProvider {
    val domainComponent: DomainComponent
}

interface DomainComponent {
    val weatherUseCase: WeatherUseCase
}
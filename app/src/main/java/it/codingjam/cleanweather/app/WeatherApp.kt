package it.codingjam.cleanweather.app

import it.codingjam.cleanweather.utils.ComponentHolderApp

class WeatherApp : ComponentHolderApp() {

    private val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(
                this
        )
    }

    override fun <C : Any> castComponent(): C {
        return applicationComponent as C
    }
}
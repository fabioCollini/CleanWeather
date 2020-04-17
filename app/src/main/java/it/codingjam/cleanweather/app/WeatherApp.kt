package it.codingjam.cleanweather.app

import inversion.InversionValidate
import it.codingjam.cleanweather.position.locationComponent
import it.codingjam.cleanweather.utils.ComponentHolderApp
import it.codingjam.cleanweather.weather.weatherComponent

@InversionValidate
class WeatherApp : ComponentHolderApp() {

    private val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(
                locationComponent(),
                weatherComponent()
        )
    }

    override fun <C : Any> castComponent(): C {
        return applicationComponent as C
    }
}
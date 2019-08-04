package it.codingjam.cleanweather.app

import android.app.Application
import inversion.InversionValidate
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.ComponentsMap

@InversionValidate
class WeatherApp : Application(), ComponentHolder by ComponentsMap()
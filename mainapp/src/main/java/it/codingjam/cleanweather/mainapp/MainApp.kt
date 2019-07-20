package it.codingjam.cleanweather.mainapp

import android.app.Application
import com.nytimes.inversion.InversionValidate
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.ComponentsMap

@InversionValidate
class MainApp : Application(), ComponentHolder by ComponentsMap()
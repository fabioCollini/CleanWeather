package it.codingjam.cleanweather.utils

import android.app.Application
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.ComponentsMap

open class ComponentHolderApp : Application(), ComponentHolder by ComponentsMap()
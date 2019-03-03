package it.codingjam.cleanweather.main

import it.codingjam.cleanweather.domain.FeatureSingleton
import javax.inject.Inject

@FeatureSingleton
class MyMainSingleton @Inject constructor() {

    init {
        println("init")
    }
}
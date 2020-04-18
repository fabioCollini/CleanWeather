package it.codingjam.cleanweather.mainapp

import it.codingjam.cleanweather.utils.ComponentHolderApp

class MainApp : ComponentHolderApp() {

    private val applicationComponent: MainApplicationComponent by lazy {
        DaggerMainApplicationComponent.factory().create(
                this
        )
    }

    override fun <C : Any> castComponent(): C {
        return applicationComponent as C
    }
}
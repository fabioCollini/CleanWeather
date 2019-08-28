package it.codingjam.cleanweather.mainapp

import androidx.test.core.app.ApplicationProvider
import it.codingjam.cleanweather.utils.ComponentHolderApp
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class ComponentHolderRule(private val init: (ComponentHolderApp) -> Unit) : TestWatcher() {
    override fun starting(description: Description?) {
        val app = ApplicationProvider.getApplicationContext<ComponentHolderApp>()
        app.clearComponents()
        init(app)
    }
}
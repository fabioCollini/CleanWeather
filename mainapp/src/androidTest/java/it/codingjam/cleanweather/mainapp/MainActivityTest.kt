package it.codingjam.cleanweather.mainapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.domain.WeatherUseCase
import it.codingjam.cleanweather.main.*
import it.codingjam.cleanweather.utils.ComponentHolder
import it.codingjam.cleanweather.utils.getOrCreate
import it.cosenonjaviste.daggermock.DaggerMock
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    val permissionManager: PermissionManager = mock {
        on(it.checkLocationPermission(any())) doReturn true
    }

    private val cityData = "CityData"

    @get:Rule
    val daggerRule = DaggerMock.rule<MainComponent> {
        customizeBuilder<MainComponent.Builder> {
            it.domainComponent(object : DomainComponent {
                override val weatherUseCase = mock<WeatherUseCase> {
                    onBlocking { it.getCityData() } doReturn cityData
                }
            }).mainDependencies(object : MainDependencies {
                override val mainNavigator: MainNavigator = mock()
            })
        }

        set {
            val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as ComponentHolder
            app.getOrCreate { it }
        }
    }


    @Test
    fun startActivity() {
        rule.launchActivity(null)

        onView(withId(R.id.result)).check(matches(withText(cityData)))
    }
}
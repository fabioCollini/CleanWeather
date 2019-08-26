package it.codingjam.cleanweather.mainapp

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.domain.WeatherUseCase
import it.codingjam.cleanweather.kotlinutils.createComponent
import it.codingjam.cleanweather.main.*
import it.codingjam.cleanweather.utils.ComponentHolderApp
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    private val permissionManager: PermissionManager = mock {
        on(it.checkLocationPermission(any())) doReturn true
    }

    private val cityData = "CityData"

    @Before
    fun setUp() {
        val app = ApplicationProvider.getApplicationContext<ComponentHolderApp>()

        app.createComponent {
            val mainDependencies = object : MainDependencies {
                override val mainNavigator: MainNavigator = mock()
            }

            val domainComponent = object : DomainComponent {
                override val weatherUseCase = mock<WeatherUseCase> {
                    onBlocking { it.getCityData() } doReturn cityData
                }
            }

            DaggerMainComponent.factory()
                    .create(
                            mainDependencies,
                            domainComponent,
                            permissionManager
                    )
        }
    }

    @Test
    fun startActivity() {
        rule.launchActivity(null)

        onView(withId(R.id.result)).check(matches(withText(cityData)))
    }
}
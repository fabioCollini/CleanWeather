package it.codingjam.cleanweather.mainapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import it.codingjam.cleanweather.domain.DomainComponent
import it.codingjam.cleanweather.domain.WeatherUseCase
import it.codingjam.cleanweather.kotlinutils.createComponent
import it.codingjam.cleanweather.main.*
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    @get:Rule
    val componentHolderRule = ComponentHolderRule { app ->
        app.createComponent {
            val mainDependencies = object : MainDependencies {
                override val mainNavigator: MainNavigator = mock()
            }

            val domainComponent = object : DomainComponent {
                override val weatherUseCase = useCase
            }

            DaggerMainComponent.factory()
                    .create(
                            mainDependencies,
                            domainComponent,
                            permissionManager
                    )
        }
    }

    private val permissionManager: PermissionManager = mock {
        on(it.checkLocationPermission(any())) doReturn true
    }

    private val cityData = "CityData"

    private val useCase = mock<WeatherUseCase> {
        onBlocking { it.getCityData() } doReturn cityData
    }

    @Test
    fun startActivity() {
        runBlocking {
            whenever(useCase.getCityData()) doReturn cityData
        }

        rule.launchActivity(null)

        onView(withId(R.id.result)).check(matches(withText(cityData)))
    }

    @Test
    fun startActivityAbc() {
        runBlocking {
            whenever(useCase.getCityData()) doReturn "ABC"
        }

        rule.launchActivity(null)

        onView(withId(R.id.result)).check(matches(withText("ABC")))
    }
}
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
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import it.codingjam.cleanweather.domain.WeatherUseCase
import it.codingjam.cleanweather.main.MainActivity
import it.codingjam.cleanweather.main.PermissionManager
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java, false, false)

    @BindValue
    @JvmField
    val permissionManager: PermissionManager = mock {
        on(it.checkLocationPermission(any())) doReturn true
    }

    private val cityData = "CityData"

    @BindValue
    @JvmField
    val useCase = mock<WeatherUseCase> {
        onBlocking { it.getCityData() } doReturn cityData
    }

    @Test
    fun startActivity() {
        rule.launchActivity(null)

        onView(withId(R.id.result)).check(matches(withText(cityData)))
    }
}
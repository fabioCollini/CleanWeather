package it.codingjam.cleanweather.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import it.codingjam.cleanweather.domain.DomainComponentProvider
import it.codingjam.cleanweather.utils.observe
import it.codingjam.cleanweather.utils.viewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Provider

private const val PERMISSIONS_REQUEST_LOCATION = 123

class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModel { viewModelProvider }

    @Inject
    lateinit var viewModelProvider: Provider<WeatherViewModel>

    @Inject
    lateinit var myMainSingleton: MyMainSingleton

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerMainComponent.builder()
                .domainComponent((application as DomainComponentProvider).domainComponent)
                .mainDependencies((application as MainDependenciesProvider).mainDependencies)
                .build()
                .inject(this)

        setContentView(R.layout.activity_main)

        root.setOnClickListener {
            mainNavigator.openDetail(this)
        }

        viewModel.state.observe(this) {
            result.text = it
        }

        if (checkLocationPermission()) {
            viewModel.load()
        }
    }

    private fun checkLocationPermission(): Boolean {
        return if (checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(ACCESS_COARSE_LOCATION),
                    PERMISSIONS_REQUEST_LOCATION)
            false
        } else {
            true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.getOrNull(0) == PERMISSION_GRANTED) {
                if (checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED) {
                    viewModel.load()
                }
            }
        }
    }
}
package it.codingjam.cleanweather.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.codingjam.cleanweather.domain.DomainComponentProvider
import it.codingjam.cleanweather.utils.getOrCreateAppComponent
import it.codingjam.cleanweather.utils.observe
import it.codingjam.cleanweather.utils.viewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModel { viewModelProvider }

    @Inject
    lateinit var viewModelProvider: Provider<WeatherViewModel>

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getOrCreateAppComponent {
            DaggerMainComponent.builder()
                    .domainComponent((application as DomainComponentProvider).domainComponent)
                    .mainDependencies((application as MainDependenciesProvider).mainDependencies)
                    .build()
        }.inject(this)

        setContentView(R.layout.activity_main)

        root.setOnClickListener {
            mainNavigator.openDetail(this)
        }

        viewModel.state.observe(this) {
            result.text = it
        }

        if (permissionManager.checkLocationPermission(this)) {
            viewModel.load()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (permissionManager.onRequestPermissionsResult(this, requestCode, permissions, grantResults)) {
            viewModel.load()
        }
    }
}
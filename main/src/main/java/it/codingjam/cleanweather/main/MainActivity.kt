package it.codingjam.cleanweather.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.utils.observe
import it.codingjam.cleanweather.utils.viewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val component by lazy { (application as ComponentHolder).mainComponent() }

    private val viewModel: WeatherViewModel by viewModel { component.weatherViewModel }

    private val permissionManager by lazy { component.permissionManager }

    private val mainNavigator by lazy { component.mainNavigator }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
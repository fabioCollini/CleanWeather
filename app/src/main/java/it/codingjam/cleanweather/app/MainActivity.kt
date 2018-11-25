package it.codingjam.cleanweather.app

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

private const val PERMISSIONS_REQUEST_LOCATION = 123

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        setContentView(R.layout.activity_main)

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

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T?) -> Unit) =
        observe(owner, Observer { observer(it) })
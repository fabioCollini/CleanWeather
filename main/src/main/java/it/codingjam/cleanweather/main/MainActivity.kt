package it.codingjam.cleanweather.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import it.codingjam.cleanweather.utils.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        root.setOnClickListener {
            mainNavigator.openDetail()
        }

        viewModel.state.observe(this) {
            result.text = it
        }

        if (permissionManager.checkLocationPermission(this)) {
            viewModel.load()
        }
        println("creating fragment...")
        val fragment = MyFragment().apply{
            arguments = bundleOf("test" to "abc")
        }
        supportFragmentManager.beginTransaction().add(R.id.container, fragment, "tag").commitNow()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (permissionManager.onRequestPermissionsResult(this, requestCode, permissions, grantResults)) {
            viewModel.load()
        }
    }
}
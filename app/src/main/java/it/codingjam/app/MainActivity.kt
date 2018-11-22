package it.codingjam.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        viewModel.state.observe(this) {
            result.text = it
        }

        searchButton.setOnClickListener {
            viewModel.load(search.text.toString())
        }
    }
}

inline fun <reified VM : ViewModel> viewModel(activity: androidx.fragment.app.FragmentActivity, crossinline factory: () -> VM): Lazy<VM> {
    return lazy {
        ViewModelProviders.of(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return factory() as T
            }
        }).get(VM::class.java)
    }
}

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T?) -> Unit) =
        observe(owner, Observer { observer(it) })
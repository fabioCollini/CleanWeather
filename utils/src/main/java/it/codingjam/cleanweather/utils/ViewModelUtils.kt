package it.codingjam.cleanweather.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*


inline fun <reified VM : ViewModel> AppCompatActivity.viewModel(crossinline provider: () -> VM): Lazy<VM> {
    return lazy {
        val factory = object : ViewModelProvider.Factory {
            override fun <T1 : ViewModel> create(aClass: Class<T1>): T1 {
                val viewModel = provider()
                return viewModel as T1
            }
        }
        ViewModelProviders.of(this, factory).get(VM::class.java)
    }
}

inline fun <reified VM : ViewModel> (() -> VM).factory(): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T1 : ViewModel> create(aClass: Class<T1>): T1 {
            val viewModel = invoke()
            return viewModel as T1
        }
    }
}

inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline observer: (T?) -> Unit) =
        observe(owner, Observer { observer(it) })
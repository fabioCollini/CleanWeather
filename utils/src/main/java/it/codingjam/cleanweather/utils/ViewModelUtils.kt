package it.codingjam.cleanweather.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import javax.inject.Provider


inline fun <reified VM : ViewModel> AppCompatActivity.viewModel(crossinline provider: () -> Provider<VM>): Lazy<VM> {
    return lazy {
        ViewModelProviders.of(this, provider().factory()).get(VM::class.java)
    }
}

inline fun <reified VM : ViewModel> Provider<VM>.factory(): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T1 : ViewModel> create(aClass: Class<T1>): T1 {
            val viewModel = get()
            return viewModel as T1
        }
    }
}

inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline observer: (T?) -> Unit) =
        observe(owner, Observer { observer(it) })
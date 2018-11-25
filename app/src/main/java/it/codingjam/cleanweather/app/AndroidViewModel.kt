package it.codingjam.cleanweather.app

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class AndroidViewModel(application: Application) : androidx.lifecycle.AndroidViewModel(application) {
    private val job = Job()

    val viewModelScope: CoroutineScope = CoroutineScope(job + Dispatchers.Main)

    final override fun onCleared() {
        job.cancel()
    }
}
package it.codingjam.app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class ViewModel : androidx.lifecycle.ViewModel() {
    private val job = Job()

    val viewModelScope: CoroutineScope = CoroutineScope(job + Dispatchers.Main)

    final override fun onCleared() {
        job.cancel()
    }
}
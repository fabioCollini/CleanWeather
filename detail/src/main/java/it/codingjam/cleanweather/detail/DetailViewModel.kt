package it.codingjam.cleanweather.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.codingjam.cleanweather.domain.WeatherUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
        private val useCase: WeatherUseCase
) : ViewModel() {

    init {
        load()
    }

    val state = MutableLiveData<String>()

    fun load() {
        viewModelScope.launch {
            val result = useCase.getForecast()
            state.value = result
        }
    }
}
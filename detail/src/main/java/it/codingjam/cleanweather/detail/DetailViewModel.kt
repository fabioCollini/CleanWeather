package it.codingjam.cleanweather.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.codingjam.cleanweather.domain.WeatherUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
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
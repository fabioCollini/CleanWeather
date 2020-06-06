package it.codingjam.cleanweather.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.codingjam.cleanweather.domain.WeatherUseCase
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(
        private val useCase: WeatherUseCase
) : ViewModel() {

    val state = MutableLiveData<String>()

    fun load() {
        viewModelScope.launch {
            val result = useCase.getCityData()
            state.value = result
        }
    }
}
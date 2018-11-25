package it.codingjam.cleanweather.app

import android.app.Application
import androidx.lifecycle.MutableLiveData
import it.codingjam.cleanweather.api.RetrofitFactory
import it.codingjam.cleanweather.api.WeatherApi
import it.codingjam.cleanweather.domain.WeatherUseCase
import it.codingjam.cleanweather.position.AndroidLocationManager
import it.codingjam.cleanweather.weather.OpenWeatherTemperatureRepository
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val api = RetrofitFactory.createService<WeatherApi>()
    private val weatherRepository = OpenWeatherTemperatureRepository(api)
    private val positionManager = AndroidLocationManager(application)
    private val useCase = WeatherUseCase(positionManager, weatherRepository)

    val state = MutableLiveData<String>()

    fun load() {
        viewModelScope.launch {
            val result = useCase.getCityData()
            state.value = result
        }
    }
}
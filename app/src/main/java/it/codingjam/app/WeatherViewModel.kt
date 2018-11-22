package it.codingjam.app

import androidx.lifecycle.MutableLiveData
import it.codingjam.cleanweather.api.RetrofitFactory
import it.codingjam.cleanweather.api.WeatherApi
import it.codingjam.cleanweather.city.JsonCityRetriever
import it.codingjam.cleanweather.domain.WeatherUseCase
import it.codingjam.cleanweather.weather.OpenWeatherTemperatureRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val api = RetrofitFactory.createService<WeatherApi>()
    private val weatherRepository = OpenWeatherTemperatureRepository(api)
    private val cityRetriever = JsonCityRetriever()
    private val useCase = WeatherUseCase(cityRetriever, weatherRepository)

    val state = MutableLiveData<String>()

    fun load(cityName: String) {
        viewModelScope.launch {
            val result = useCase.getCityData(cityName)
            state.value = result
        }
    }
}
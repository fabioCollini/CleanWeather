package it.codingjam.cleanweather.domain


class WeatherUseCase(
        private val cityRetriever: CityRetriever,
        private val repository: TemperatureRepository) {

    suspend fun getCityData(s: String): String {
        return try {
            val cities = cityRetriever.findCity(s)

            if (cities.isEmpty()) {
                "No city found"
            } else {
                cities.map { city ->
                    val temperature = repository.getTemperature(city.id)
                    "$city - $temperature"
                }.joinToString("\n")
            }
        } catch (e: Exception) {
            "Error retrieving data: ${e.message}"
        }
    }
}
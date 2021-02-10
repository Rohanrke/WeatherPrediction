package com.rohan.weatherprediction.domain.entity

data class CurrentWeatherEntity(
    val visibility: Int? = null,
    val timezone: Int? = null,
    val main: MainDataEntity? = null,
    val clouds: Int? = null,
    val country: String? = null,
    val dt: Int? = null,
    val lon: Double?,
    val lat: Double?,
    val weather: List<WeatherItemEntity?>? = null,
    val cityName: String? = null,
    val cod: Int? = null,
    val id: Int? = null,
    val base: String? = null,
    val wind: WindEntity? = null){

    fun getCurrentWeather(): WeatherItemEntity? {
        return weather?.firstOrNull()
    }

}
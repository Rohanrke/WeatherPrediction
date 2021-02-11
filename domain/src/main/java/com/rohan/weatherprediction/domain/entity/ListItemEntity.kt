package com.rohan.weatherprediction.domain.entity

import java.time.DayOfWeek

data class ListItemEntity(
    val dt: Long?,
    val rain: RainEntity?,
    val dtTxt: String?,
    val snowEntity: SnowEntity?,
    val weather: List<WeatherItemEntity?>?,
    val main: MainDataEntity?,
    val clouds: Int?,
    val country: String?,
    val wind: WindEntity?,
    var dayOfWeek: DayOfWeek = DayOfWeek.MONDAY,
    var tempMinVariance: Boolean = false,
    var tempMaxVariance: Boolean = false
){

    fun getHourOfDay(): String {
        return dtTxt?.substringAfter(" ")?.substringBeforeLast(":") ?: "00:00"
    }

    fun getWeatherItem(): WeatherItemEntity? {
        return weather?.firstOrNull()
    }
}


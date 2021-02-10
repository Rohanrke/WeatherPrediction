package com.rohan.weatherprediction.domain.entity

data class ForecastEntity(
    val city: CityEntity?,
    val cnt: Int?,
    val cod: String?,
    val message: Double?,
    var list: List<ListItemEntity>?
)

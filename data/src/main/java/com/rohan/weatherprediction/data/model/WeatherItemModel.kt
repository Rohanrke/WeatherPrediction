package com.rohan.weatherprediction.data.model

import com.google.gson.annotations.SerializedName

data class WeatherItemModel(
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("main")
    val main: String?,

    @SerializedName("id")
    val id: Int?
)
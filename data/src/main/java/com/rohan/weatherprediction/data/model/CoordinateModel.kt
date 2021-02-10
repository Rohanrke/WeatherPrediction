package com.rohan.weatherprediction.data.model


import com.google.gson.annotations.SerializedName


data class CoordinateModel(
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("lat")
    val lat: Double?
)

package com.rohan.weatherprediction.data.model

import com.google.gson.annotations.SerializedName

data class WindModel(
    @SerializedName("deg")
    val deg: Double?,
    @SerializedName("speed")
    val speed: Double?
)

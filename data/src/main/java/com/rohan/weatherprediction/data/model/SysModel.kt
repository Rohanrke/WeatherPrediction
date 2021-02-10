package com.rohan.weatherprediction.data.model


import com.google.gson.annotations.SerializedName

data class SysModel(
    @SerializedName("country")
    val country: String
)

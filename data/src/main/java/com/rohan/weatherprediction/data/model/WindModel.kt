package com.rohan.weatherprediction.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class WindModel(
    @SerializedName("deg")
    val deg: Double?,
    @SerializedName("speed")
    val speed: Double?
)

package com.rohan.weatherprediction.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class SysModel(
    @SerializedName("country")
    val country: String
)

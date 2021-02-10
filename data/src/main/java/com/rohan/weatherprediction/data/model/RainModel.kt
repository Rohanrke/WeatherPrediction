package com.rohan.weatherprediction.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class RainModel(@SerializedName("3h") val jsonMember3h: Double?)

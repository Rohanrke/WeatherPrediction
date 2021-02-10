package com.rohan.weatherprediction.data.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class CityModel(

    @SerializedName("country")
    val country: String?,

    @SerializedName("coord")
    @Embedded(prefix = "coordinate")
    val coord: CoordinateModel?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("id")
    val id: Int?
)
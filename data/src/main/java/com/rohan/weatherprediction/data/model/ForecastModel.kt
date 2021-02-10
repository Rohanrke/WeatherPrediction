package com.rohan.weatherprediction.data.model

import androidx.annotation.NonNull
import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "forecast_weather")
data class ForecastModel(
    @SerializedName("city")
    @Embedded(prefix = "city")
    val city: CityModel?,

    @SerializedName("cnt")
    val cnt: Int?,

    @SerializedName("cod")
    val cod: String,

    @SerializedName("message")
    val message: Double?,

    @SerializedName("list")
    val list: List<ListItemModel>?,

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0
)

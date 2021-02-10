package com.rohan.weatherprediction.data.model

import com.google.gson.annotations.SerializedName

data class MainDataModel(

    @SerializedName("temp")
    val temp: Double?,

    @SerializedName("temp_min")
    var tempMin: Double?,

    @SerializedName("grnd_level")
    val grndLevel: Double?,

    @SerializedName("temp_kf")
    val tempKf: Double?,

    @SerializedName("humidity")
    val humidity: Int?,

    @SerializedName("pressure")
    val pressure: Double?,

    @SerializedName("sea_level")
    val seaLevel: Double?,

    @SerializedName("temp_max")
    var tempMax: Double?
)






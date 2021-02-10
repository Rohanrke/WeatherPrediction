package com.rohan.weatherprediction.data.model


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class ListItemModel(

    @SerializedName("dt")
    val dt: Long?,

    @SerializedName("rain")
    @Embedded(prefix = "rain")
    val rain: RainModel?,

    @SerializedName("dt_txt")
    val dtTxt: String?,

    @SerializedName("snow")
    @Embedded(prefix = "snow")
    val snowModel: SnowModel?,

    @SerializedName("weather")
    val weather: List<WeatherItemModel?>?,

    @SerializedName("main")
    @Embedded(prefix = "mainData")
    val main: MainDataModel?,

    @SerializedName("clouds")
    @Embedded(prefix = "cloud")
    val clouds: CloudsModel?,

    @SerializedName("sys")
    @Embedded(prefix = "sys")
    val sys: SysModel?,

    @SerializedName("wind")
    @Embedded(prefix = "wind")
    val wind: WindModel?
)
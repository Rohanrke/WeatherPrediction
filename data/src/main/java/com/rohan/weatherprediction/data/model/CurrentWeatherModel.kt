package com.rohan.weatherprediction.data.model

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.rohan.weatherprediction.data.local.WeatherItemTypeConverters

@Entity(tableName = "current_weather")
data class CurrentWeatherModel(

    @SerializedName( "visibility")
    val visibility: Int? = null,

    @SerializedName( "timezone")
    val timezone: Int? = null,

    @SerializedName( "main")
    @Embedded(prefix = "maindata")
    val main: MainDataModel? = null,

    @SerializedName( "clouds")
    @Embedded(prefix = "cloud")
    val clouds: CloudsModel? = null,

    @SerializedName( "sys")
    @Embedded(prefix = "sys")
    val sys: SysModel? = null,

    @SerializedName( "dt")
    val dt: Int? = null,

    @SerializedName( "coord")
    @Embedded(prefix = "coordinate")
    val coord: CoordinateModel? = null,

    @SerializedName( "weather")
    val weather: List<WeatherItemModel?>? = null,

    @SerializedName( "name")
    val name: String? = null,

    @SerializedName( "cod")
    val cod: Int? = null,

    @SerializedName( "id")
    val id: Int? = null,

    @SerializedName( "base")
    val base: String? = null,

    @SerializedName( "wind")
    @Embedded(prefix = "wind")
    val wind: WindModel? = null,

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var itemId: Int = 0)
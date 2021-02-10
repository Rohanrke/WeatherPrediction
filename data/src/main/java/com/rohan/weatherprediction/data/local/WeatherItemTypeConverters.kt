package com.rohan.weatherprediction.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rohan.weatherprediction.data.model.WeatherItemModel

class WeatherItemTypeConverters {


    @TypeConverter
    fun fromWeatherItemModelList(value: List<WeatherItemModel>): String {
        val gson = Gson()
        val type = object : TypeToken<List<WeatherItemModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toWeatherItemModelList(value: String): List<WeatherItemModel> {
        val gson = Gson()
        val type = object : TypeToken<List<WeatherItemModel>>() {}.type
        return gson.fromJson(value, type)
    }
}
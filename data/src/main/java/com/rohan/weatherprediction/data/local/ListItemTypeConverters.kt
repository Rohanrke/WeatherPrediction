package com.rohan.weatherprediction.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rohan.weatherprediction.data.model.ListItemModel

class ListItemTypeConverters {
    @TypeConverter
    fun fromListItemModelList(value: List<ListItemModel>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ListItemModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toListItemModelList(value: String): List<ListItemModel> {
        val gson = Gson()
        val type = object : TypeToken<List<ListItemModel>>() {}.type
        return gson.fromJson(value, type)
    }

}
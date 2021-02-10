package com.rohan.weatherprediction.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rohan.weatherprediction.data.model.CurrentWeatherModel

@Dao
interface CurrentWeatherDao {

    @Query("select * from current_weather")
    suspend fun getCurrentWeather(): List<CurrentWeatherModel>

    @Insert
    suspend fun insertAll(currentWeatherModel: CurrentWeatherModel)

    @Query("DELETE FROM current_weather")
    suspend fun clear()

}
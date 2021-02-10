package com.rohan.weatherprediction.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rohan.weatherprediction.data.model.ForecastModel

@Dao
interface ForecastWeatherDao {

    @Query("select * from forecast_weather")
    suspend fun getForecastWeather(): List<ForecastModel>

    @Insert
    suspend fun insertAll(forecastModel: ForecastModel)

    @Query("DELETE FROM forecast_weather")
    suspend fun clear()
}
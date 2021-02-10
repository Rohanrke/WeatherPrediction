package com.rohan.weatherprediction.data.local

import com.rohan.weatherprediction.data.model.CurrentWeatherModel
import com.rohan.weatherprediction.data.model.ForecastModel

interface IWeatherStore {

    suspend fun getCurrentWeather(): CurrentWeatherModel?
    suspend fun insertCurrentWeather(currentWeatherModel: CurrentWeatherModel)
    suspend fun deleteAllCurrent()

    suspend fun getForecastWeather(): ForecastModel?
    suspend fun insertForecastWeather(model: ForecastModel)
    suspend fun deleteAllForecast()

}
package com.rohan.weatherprediction.data.local

import com.rohan.weatherprediction.data.model.CurrentWeatherModel
import com.rohan.weatherprediction.data.model.ForecastModel

class WeatherStore(private val currentDao: CurrentWeatherDao,
  private val forecastDao: ForecastWeatherDao): IWeatherStore {
    override suspend fun getCurrentWeather(): CurrentWeatherModel? {
       return currentDao.getCurrentWeather().firstOrNull()
    }

    override suspend fun insertCurrentWeather(currentWeatherModel: CurrentWeatherModel) {
        currentDao.insertAll(currentWeatherModel)
    }

    override suspend fun deleteAllCurrent() {
        currentDao.clear()
    }

    override suspend fun getForecastWeather(): ForecastModel? {
        return forecastDao.getForecastWeather().firstOrNull()
    }

    override suspend fun insertForecastWeather(model: ForecastModel) {
        forecastDao.insertAll(model)
    }

    override suspend fun deleteAllForecast() {
        forecastDao.clear()
    }
}
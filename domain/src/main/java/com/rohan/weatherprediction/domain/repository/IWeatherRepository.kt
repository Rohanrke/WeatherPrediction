package com.rohan.weatherprediction.domain.repository

import com.rohan.weatherprediction.domain.arch.ResultState
import com.rohan.weatherprediction.domain.entity.CurrentWeatherEntity
import com.rohan.weatherprediction.domain.entity.ForecastEntity

interface IWeatherRepository {

    suspend fun getCurrentWeather(cityName: String): ResultState<CurrentWeatherEntity>

    suspend fun getForecastWeather(cityName: String): ResultState<ForecastEntity>
}
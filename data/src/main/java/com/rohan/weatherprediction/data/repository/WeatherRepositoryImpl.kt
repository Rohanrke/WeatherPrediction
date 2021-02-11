package com.rohan.weatherprediction.data.repository

import androidx.annotation.VisibleForTesting
import com.rohan.weatherprediction.data.BuildConfig
import com.rohan.weatherprediction.data.FailureHandler
import com.rohan.weatherprediction.data.local.IWeatherStore
import com.rohan.weatherprediction.data.mapper.weather.CurrentWeatherMapper
import com.rohan.weatherprediction.data.mapper.weather.ForecastMapper
import com.rohan.weatherprediction.data.remote.service.WeatherService
import com.rohan.weatherprediction.domain.arch.ResultState
import com.rohan.weatherprediction.domain.entity.CurrentWeatherEntity
import com.rohan.weatherprediction.domain.entity.ForecastEntity
import com.rohan.weatherprediction.domain.repository.IWeatherRepository

class WeatherRepositoryImpl(
    private val remote: WeatherService,
    private val local: IWeatherStore,
    private val currentMapper: CurrentWeatherMapper,
    private val forecastMapper: ForecastMapper,
    private val failureHandler: FailureHandler
) : IWeatherRepository {
    override suspend fun getCurrentWeather(cityName: String): ResultState<CurrentWeatherEntity> {
        return try {
            val response =
                remote.getCurrentByCityName(cityName = cityName, appId = BuildConfig.API_KEY)
            local.deleteAllCurrent()
            local.insertCurrentWeather(response)
            ResultState.Success(currentMapper.map(response))
        } catch (ex: Exception) {
            val cached = cachedCurrentWeather()
            if (cached != null) ResultState.Success(cached)
            else ResultState.Error(failureHandler.handleException(ex))
        }
    }

    override suspend fun getForecastWeather(
        cityName: String
    ): ResultState<ForecastEntity> {
        return try {
            val response = remote.getForecastByCityName(
                cityName = cityName,
                appId = BuildConfig.API_KEY
            )
            local.deleteAllForecast()
            local.insertForecastWeather(response)
            ResultState.Success(forecastMapper.map(response))
        } catch (ex: Exception) {
            val cached = cachedForecastWeather()
            if (cached != null) ResultState.Success(cached)
            else ResultState.Error(failureHandler.handleException(ex))
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    suspend fun cachedCurrentWeather(): CurrentWeatherEntity? {
        return local.getCurrentWeather()?.let {
            currentMapper.map(it)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    suspend fun cachedForecastWeather(): ForecastEntity? {
        return local.getForecastWeather()?.let {
            forecastMapper.map(it)
        }
    }

}
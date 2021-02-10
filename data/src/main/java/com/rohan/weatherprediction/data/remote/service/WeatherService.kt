package com.rohan.weatherprediction.data.remote.service


import com.rohan.weatherprediction.data.model.CurrentWeatherModel
import com.rohan.weatherprediction.data.model.ForecastModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast")
    suspend fun getForecastByCityName(
        @Query("q")
        cityName: String,
        @Query("appid")
        appId: String,
    ): ForecastModel

    @GET("weather")
    suspend fun getCurrentByCityName(
        @Query("q") cityName: String,
        @Query("appid") appId: String,
    ): CurrentWeatherModel
}


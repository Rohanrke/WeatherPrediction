package com.rohan.weatherprediction.data.di

import com.rohan.weatherprediction.data.local.AppDatabase
import com.rohan.weatherprediction.data.local.IWeatherStore
import com.rohan.weatherprediction.data.local.WeatherStore
import org.koin.dsl.module

val dbModule = module {
    single { AppDatabase(get()) }
    factory { getCurrentDao(get()) }
    factory { getForecastDao(get()) }
    factory<IWeatherStore> { WeatherStore(get(), get()) }
}

fun getCurrentDao(appDatabase: AppDatabase) = appDatabase.currentDao()
fun getForecastDao(appDatabase: AppDatabase) = appDatabase.forecastDao()

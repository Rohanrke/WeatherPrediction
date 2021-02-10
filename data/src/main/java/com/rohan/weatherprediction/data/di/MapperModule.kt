package com.rohan.weatherprediction.data.di


import com.rohan.weatherprediction.data.mapper.weather.*
import org.koin.dsl.module

fun mapperModule() = module {

    factory { CityMapper() }
    factory { ListItemMapper(get(), get(), get()) }
    factory { MainDataMapper() }
    factory { WindMapper() }
    factory { ForecastMapper(get(), get()) }
    factory { CurrentWeatherMapper(get(), get(), get()) }
    factory { WeatherItemMapper() }
}
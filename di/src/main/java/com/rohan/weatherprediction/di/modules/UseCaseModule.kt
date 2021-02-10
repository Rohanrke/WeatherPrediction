package com.rohan.weatherprediction.di.modules


import com.rohan.weatherprediction.domain.usecase.GetCurrentWeatherUseCase
import com.rohan.weatherprediction.domain.usecase.GetForecastWeatherUseCase
import com.rohan.weatherprediction.domain.usecase.SearchCityUseCase
import org.koin.dsl.module

fun useCaseModule() = module {
    factory { GetCurrentWeatherUseCase(get()) }
    factory { GetForecastWeatherUseCase(get()) }
    factory { SearchCityUseCase(get()) }
}
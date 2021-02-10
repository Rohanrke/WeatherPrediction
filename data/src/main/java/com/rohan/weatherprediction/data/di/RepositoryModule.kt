package com.rohan.weatherprediction.data.di


import com.rohan.weatherprediction.data.repository.CountryRepositoryImpl
import com.rohan.weatherprediction.data.repository.WeatherRepositoryImpl
import com.rohan.weatherprediction.domain.repository.ICountryRepository
import com.rohan.weatherprediction.domain.repository.IWeatherRepository
import org.koin.dsl.module

fun repositoryModule() = module {
    factory<IWeatherRepository> { WeatherRepositoryImpl(get(), get(), get(), get(), get()) }
    factory<ICountryRepository> { CountryRepositoryImpl(get(), get()) }
}
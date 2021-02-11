package com.rohan.weatherprediction.di

import com.rohan.weatherprediction.config.RemoteConfig
import com.rohan.weatherprediction.preferences.BaseSharedPreferences
import com.rohan.weatherprediction.utils.NetworkUtils
import org.koin.dsl.module


fun appModule() = module { 
    factory { NetworkUtils(get()) }
    factory { BaseSharedPreferences(get()) }
    factory { RemoteConfig(get()) }
}
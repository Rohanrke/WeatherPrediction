package com.rohan.weatherprediction.data.di

import org.koin.core.context.loadKoinModules


fun loadDataModules() {
    // Add module of data layer
    loadKoinModules(listOf(retrofitModule(), repositoryModule(), mapperModule(), dbModule))
}
package com.rohan.weatherprediction.di.modules

import com.rohan.weatherprediction.data.di.loadDataModules
import org.koin.core.context.loadKoinModules


fun initKoin() {
    loadKoinModules(listOf(mainModule(), useCaseModule()))
    // Add module of data layer
}
fun loadDataModule() {
    loadDataModules()
    // Add module of data layer
}


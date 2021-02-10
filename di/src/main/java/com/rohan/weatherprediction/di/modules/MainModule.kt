package com.rohan.weatherprediction.di.modules

import com.rohan.weatherprediction.data.FailureHandler
import org.koin.dsl.module


fun mainModule() = module {
    factory { FailureHandler() }
}
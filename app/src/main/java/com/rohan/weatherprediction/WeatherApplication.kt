package com.rohan.weatherprediction

import android.app.Application
import android.content.Context
import com.rohan.weatherprediction.di.appModule
import com.rohan.weatherprediction.di.modules.initKoin
import com.rohan.weatherprediction.di.modules.loadDataModule
import com.rohan.weatherprediction.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        println("--attachBaseContext--")
        startKoin {
            androidContext(this@WeatherApplication)
            modules(
                mutableListOf(
                    viewModelModule(), appModule()
                )
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        initKoin()
        loadDataModule()
    }
}
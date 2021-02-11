package com.rohan.weatherprediction.config

import android.content.Context

/**
 *
 * This class can be used for remote configuration values
 *
 * Like we cam update temp -- variance value either by config api or by Firebase Remote configs etc
 *
 */
class RemoteConfig(private val context: Context) {



    fun getMinTempVariance(): Double{
        return VARIANCE_MIN_TEMP
    }

    fun getMaxTempVariance(): Double{
        return VARIANCE_MAX_TEMP
    }


    fun getReportDetailItemCount(): Int{
        return WEATHER_DETAIL_COUNT
    }

    companion object{
       private const val VARIANCE_MIN_TEMP = 5.0
       private const val VARIANCE_MAX_TEMP = 30.0


       // use to fetch no of item of weather details from weather api
       private const val WEATHER_DETAIL_COUNT = 25
    }
}
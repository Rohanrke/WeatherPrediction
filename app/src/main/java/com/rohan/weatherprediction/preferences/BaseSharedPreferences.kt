package com.rohan.weatherprediction.preferences

import android.content.Context
import android.content.SharedPreferences

class BaseSharedPreferences(private val context: Context) {

    companion object {
        private const val KEY_CITY = "SAVED_CITY"
    }

    private var preferences: SharedPreferences? = null

    private fun getSharedPreferences(): SharedPreferences? {
        if (preferences == null) {
            preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        }
        return preferences
    }


    fun getSavedCity(): String? {
        return getSharedPreferences()?.getString(KEY_CITY, null)
    }

    fun saveCity(city: String) {
        getSharedPreferences()?.edit()?.run {
            putString(KEY_CITY, city)
            apply()
        }
    }

}
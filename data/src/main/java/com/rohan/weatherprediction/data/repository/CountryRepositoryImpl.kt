package com.rohan.weatherprediction.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rohan.weatherprediction.data.FailureHandler
import com.rohan.weatherprediction.data.readTextFromAsset
import com.rohan.weatherprediction.domain.arch.ResultState
import com.rohan.weatherprediction.domain.repository.ICountryRepository

class CountryRepositoryImpl(
    private val context: Context,
    private val failureHandler: FailureHandler
) : ICountryRepository {
    override suspend fun getCountryCityMap(): ResultState<Map<String, List<String>>> {
        return try {
            val data = context.readTextFromAsset("countriesToCities.json")
            val gson = Gson()
            val type = object : TypeToken<Map<String, List<String>>>() {}.type
            val result: Map<String, List<String>> = gson.fromJson(data, type)
            ResultState.Success(result)
        } catch (ex: Exception) {
            ResultState.Error(failureHandler.handleException(ex))
        }
    }
}
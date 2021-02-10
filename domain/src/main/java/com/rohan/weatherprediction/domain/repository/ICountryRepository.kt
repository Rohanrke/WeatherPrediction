package com.rohan.weatherprediction.domain.repository

import com.rohan.weatherprediction.domain.arch.ResultState

interface ICountryRepository {

    suspend fun getCountryCityMap(): ResultState<Map<String, List<String>>>
}
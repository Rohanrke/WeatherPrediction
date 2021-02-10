package com.rohan.weatherprediction.domain.usecase

import com.rohan.weatherprediction.domain.arch.BaseCase
import com.rohan.weatherprediction.domain.arch.Either
import com.rohan.weatherprediction.domain.arch.Failure
import com.rohan.weatherprediction.domain.arch.result
import com.rohan.weatherprediction.domain.repository.ICountryRepository

class SearchCityUseCase(private val repository: ICountryRepository) :
    BaseCase<Map<String, List<String>>>() {
    override suspend fun run(): Either<Failure, Map<String, List<String>>> {
        return repository.getCountryCityMap().result()
    }
}
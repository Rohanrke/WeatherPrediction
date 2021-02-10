package com.rohan.weatherprediction.domain.usecase

import com.rohan.weatherprediction.domain.arch.BaseParamCase
import com.rohan.weatherprediction.domain.arch.Either
import com.rohan.weatherprediction.domain.arch.Failure
import com.rohan.weatherprediction.domain.arch.result
import com.rohan.weatherprediction.domain.entity.CurrentWeatherEntity
import com.rohan.weatherprediction.domain.repository.IWeatherRepository

class GetCurrentWeatherUseCase(private val repository: IWeatherRepository) :
    BaseParamCase<CurrentWeatherEntity, String>() {

    override suspend fun run(params: String): Either<Failure, CurrentWeatherEntity> {
        return repository.getCurrentWeather(cityName = params).result()
    }

}
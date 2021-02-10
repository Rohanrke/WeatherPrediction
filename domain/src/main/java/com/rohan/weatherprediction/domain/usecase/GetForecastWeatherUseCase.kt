package com.rohan.weatherprediction.domain.usecase

import com.rohan.weatherprediction.domain.arch.BaseParamCase
import com.rohan.weatherprediction.domain.arch.Either
import com.rohan.weatherprediction.domain.arch.Failure
import com.rohan.weatherprediction.domain.arch.result
import com.rohan.weatherprediction.domain.entity.ForecastEntity
import com.rohan.weatherprediction.domain.repository.IWeatherRepository

class GetForecastWeatherUseCase(private val repository: IWeatherRepository) :
    BaseParamCase<ForecastEntity, String>() {

    override suspend fun run(params: String): Either<Failure, ForecastEntity> {
        return repository.getForecastWeather(cityName = params).result()
    }
}

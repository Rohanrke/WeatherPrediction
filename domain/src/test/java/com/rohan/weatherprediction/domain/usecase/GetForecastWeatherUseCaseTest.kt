package com.rohan.weatherprediction.domain.usecase

import com.rohan.weatherprediction.domain.arch.Failure
import com.rohan.weatherprediction.domain.arch.ResultState
import com.rohan.weatherprediction.domain.entity.*
import com.rohan.weatherprediction.domain.repository.IWeatherRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class GetForecastWeatherUseCaseTest {
    @ExperimentalCoroutinesApi
    private val scope = TestCoroutineScope()

    private val dispatcher = Dispatchers.Unconfined

    @MockK
    private lateinit var repository: IWeatherRepository
    private lateinit var useCase: GetForecastWeatherUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetForecastWeatherUseCase(repository)
    }

    @Test
    fun getForecastWeather_SuccessTest() {
        val city = "New Delhi"
        val entity =
            ForecastEntity(
                city = CityEntity(
                    country = "India",
                    lat = 27.987,
                    lon = 78.0999,
                    name = "Mumbai",
                    id = 22
                ),
                cnt = 0, message = 23.4, list = null,
                cod = "200",
            )


        coEvery { repository.getForecastWeather(city) } returns ResultState.Success(
            entity
        )
        val actual = arrayListOf<ForecastEntity>()
        useCase.invoke(scope, city, dispatcher) { either ->
            either.either({
                it
            }, {
                actual.add(it)
            })
        }
        Assert.assertEquals(entity, actual[0])
    }

    @Test
    fun getForecastWeather_FailureTest() {
        val city = "New Delhi"
        val failure =
            Failure(
                1,
                "Please check your internet connection and try again",
                UnknownHostException()
            )
        coEvery { repository.getForecastWeather(city) } returns ResultState.Error(
            failure
        )
        var actual: Failure? = null
        useCase.invoke(scope, city, dispatcher) { either ->
            either.either({
                actual = it
                it
            }, {
            })
        }
        Assert.assertEquals(failure, actual)
    }
}

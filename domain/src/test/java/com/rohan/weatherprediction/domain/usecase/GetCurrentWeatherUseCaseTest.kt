package com.rohan.weatherprediction.domain.usecase

import com.rohan.weatherprediction.domain.arch.Failure
import com.rohan.weatherprediction.domain.arch.ResultState
import com.rohan.weatherprediction.domain.entity.CurrentWeatherEntity
import com.rohan.weatherprediction.domain.entity.MainDataEntity
import com.rohan.weatherprediction.domain.entity.WeatherItemEntity
import com.rohan.weatherprediction.domain.entity.WindEntity
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

class GetCurrentWeatherUseCaseTest {

    @ExperimentalCoroutinesApi
    private val scope = TestCoroutineScope()

    private val dispatcher = Dispatchers.Unconfined

    @MockK
    private lateinit var repository: IWeatherRepository
    private lateinit var useCase: GetCurrentWeatherUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetCurrentWeatherUseCase(repository)
    }

    @Test
    fun getCurrentWeather_SuccessTest() {
        val city = "New Delhi"
        val currentWeatherEntity =
            CurrentWeatherEntity(
                visibility = 1,
                timezone = 112,
                main = MainDataEntity(
                    temp = 21.2,
                    tempMin = 12.0,
                    tempMax = 40.0,
                    tempKf = 30.0,
                    seaLevel = 22.0,
                    grndLevel = 2.0,
                    pressure = 12.0,
                    humidity = 24
                ),
                clouds = 90,
                country = "India",
                dt = 1233444,
                lon = 27.999,
                lat = 76.0090,
                weather = emptyList<WeatherItemEntity>(),
                cityName = "New Delhi",
                cod = 200,
                id = 123,
                base = "weather",
                wind = WindEntity(deg = 12.0, speed = 122.0)
            )


        coEvery { repository.getCurrentWeather(city) } returns ResultState.Success(
            currentWeatherEntity
        )
        val actual = arrayListOf<CurrentWeatherEntity>()
        useCase.invoke(scope,city, dispatcher) { either ->
            either.either({
                it
            }, {
                actual.add(it)
            })
        }
        Assert.assertEquals(currentWeatherEntity, actual[0])
    }

    @Test
    fun getCurrentWeather_FailureTest() {
        val city = "New Delhi"
        val failure =
            Failure(
                1,
                "Please check your internet connection and try again",
                UnknownHostException()
            )
        coEvery { repository.getCurrentWeather(city)} returns ResultState.Error(
            failure
        )
        var actual: Failure? = null
        useCase.invoke(scope,city, dispatcher) { either ->
            either.either({
                actual = it
                it
            }, {
            })
        }
        Assert.assertEquals(failure, actual)
    }
}
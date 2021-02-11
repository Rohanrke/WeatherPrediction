package com.rohan.weatherprediction.data.repository

import com.rohan.weatherprediction.data.FailureHandler
import com.rohan.weatherprediction.data.local.IWeatherStore
import com.rohan.weatherprediction.data.mapper.weather.CurrentWeatherMapper
import com.rohan.weatherprediction.data.mapper.weather.ForecastMapper
import com.rohan.weatherprediction.data.remote.service.WeatherService
import com.rohan.weatherprediction.domain.arch.ResultState
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class WeatherRepositoryImplTest {
    @MockK
    private lateinit var weatherService: WeatherService

    @MockK
    private lateinit var weatherStore: IWeatherStore

    @MockK
    private lateinit var currentMapper: CurrentWeatherMapper

    @MockK
    private lateinit var forecastMapper: ForecastMapper

    @MockK
    private lateinit var failureHandler: FailureHandler

    private lateinit var repository: WeatherRepositoryImpl


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = WeatherRepositoryImpl(
            remote = weatherService,
            local = weatherStore,
            currentMapper = currentMapper,
            forecastMapper = forecastMapper,
            failureHandler = failureHandler
        )
    }

    @Test
    fun `success from server should clear local storage and insert new weather report`() {
        val city = "Chennai"

        val currentModel = TestUtils.getCurrentModel()
        val currentEntity = TestUtils.getCurrentEntity()

        coEvery { weatherService.getCurrentByCityName(city, any()) } returns currentModel
        coJustRun { weatherStore.insertCurrentWeather(any()) }
        coJustRun { weatherStore.deleteAllCurrent() }
        coEvery { weatherStore.getCurrentWeather() } returns currentModel
        every { currentMapper.map(currentModel) } returns currentEntity

        runBlocking {
            val result = repository.getCurrentWeather(city)
            Assert.assertEquals(ResultState.Success((currentEntity)), result)
        }
        coVerify(exactly = 1) { weatherStore.deleteAllCurrent() }
        coVerify(exactly = 1) { weatherStore.insertCurrentWeather(currentModel) }
        coVerify(exactly = 0) { weatherStore.getCurrentWeather() }
    }

    @Test
    fun `cached current weather should fetch from DB`() {
        val currentModel = TestUtils.getCurrentModel()
        val currentEntity = TestUtils.getCurrentEntity()
        every { currentMapper.map(currentModel) } returns currentEntity

        coEvery { weatherStore.getCurrentWeather() } returns currentModel
        runBlocking {
            val result = repository.cachedCurrentWeather()
            Assert.assertEquals(currentEntity, result)
        }

        coVerify(exactly = 1) { weatherStore.getCurrentWeather() }

    }
}
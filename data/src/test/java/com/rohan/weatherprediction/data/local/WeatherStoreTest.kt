package com.rohan.weatherprediction.data.local

import com.rohan.weatherprediction.data.repository.TestUtils
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class WeatherStoreTest{

    private lateinit var store: IWeatherStore

    @MockK
    private lateinit var weatherDao: CurrentWeatherDao

    @MockK
    private lateinit var forecastDao: ForecastWeatherDao

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        store = WeatherStore(weatherDao, forecastDao)
    }

    @Test
    fun getCurrentWeather_Test() {
        val list = listOf(TestUtils.getCurrentModel())
        coEvery { weatherDao.getCurrentWeather() } returns list

        runBlocking {
            val actual = store.getCurrentWeather()
            Assert.assertEquals(list.firstOrNull(), actual)
        }
        coVerify (exactly = 1) { weatherDao.getCurrentWeather() }
    }

    @Test
    fun insertAllCurrent_Test() {
        val model = TestUtils.getCurrentModel()
        coJustRun { weatherDao.insertAll(model) }

        runBlocking {
            store.insertCurrentWeather(model)
        }
        coVerify(exactly = 1) { weatherDao.insertAll(model) }
    }

    @Test
    fun deleteAllCurrent_Test() {
        coJustRun { weatherDao.clear() }

        runBlocking {
            store.deleteAllCurrent()
        }
        coVerify(exactly = 1) { weatherDao.clear() }
    }

    @Test
    fun getForecastWeather_Test() {
        val list = listOf(TestUtils.getForecastModel())
        coEvery { forecastDao.getForecastWeather() } returns list

        runBlocking {
            val actual = store.getForecastWeather()
            Assert.assertEquals(list.firstOrNull(), actual)
        }
        coVerify (exactly = 1) { forecastDao.getForecastWeather() }
    }

    @Test
    fun insertAllForecast_Test() {
        val model = TestUtils.getForecastModel()
        coJustRun { forecastDao.insertAll(model) }

        runBlocking {
            store.insertForecastWeather(model)
        }
        coVerify(exactly = 1) { forecastDao.insertAll(model) }
    }

    @Test
    fun deleteAllForecast_Test() {
        coJustRun { forecastDao.clear() }

        runBlocking {
            store.deleteAllForecast()
        }
        coVerify(exactly = 1) { forecastDao.clear() }
    }

}
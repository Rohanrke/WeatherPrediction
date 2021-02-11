package com.rohan.weatherprediction.domain.usecase

import com.rohan.weatherprediction.domain.arch.Failure
import com.rohan.weatherprediction.domain.arch.ResultState
import com.rohan.weatherprediction.domain.repository.ICountryRepository
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

class SearchCityUseCaseTest{

    @ExperimentalCoroutinesApi
    private val scope = TestCoroutineScope()

    private val dispatcher = Dispatchers.Unconfined

    @MockK
    private lateinit var repository: ICountryRepository
    private lateinit var useCase: SearchCityUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = SearchCityUseCase(repository)
    }

    @Test
    fun getCountryCityMap_SuccessTest() {
        val pair1 = Pair("India", listOf("New Delhi", "Mumbai"))
        val pair2 = Pair("Us", listOf("New York", "San Francisco"))

        val map = mapOf(pair1, pair2)

        coEvery { repository.getCountryCityMap() } returns ResultState.Success(
            map
        )
        val actual = mutableMapOf<String, List<String>>()
        useCase.invoke(scope, dispatcher) { either ->
            either.either({
                it
            }, {
                actual.putAll(it)
            })
        }
        Assert.assertEquals(map, actual)
    }

    @Test
    fun getCountryCityMap_FailureTest() {
        val failure =
            Failure(
                1,
                "Please check your internet connection and try again",
                UnknownHostException()
            )
        coEvery { repository.getCountryCityMap()} returns ResultState.Error(
            failure
        )
        var actual: Failure? = null
        useCase.invoke(scope, dispatcher) { either ->
            either.either({
                actual = it
                it
            }, {
            })
        }
        Assert.assertEquals(failure, actual)
    }
}
package com.rohan.weatherprediction.data.mapper.weather

import com.rohan.weatherprediction.data.repository.TestUtils
import org.junit.Assert
import org.junit.Test

class WeatherItemMapperTest{
    private val mapper = WeatherItemMapper()

    @Test
    fun map() {
        val model = TestUtils.getWeatherItemModel()
        val entity = TestUtils.getWeatherItemEntity()

        val expected = mapper.map(remote = model)
        Assert.assertEquals(expected, entity)
    }

}
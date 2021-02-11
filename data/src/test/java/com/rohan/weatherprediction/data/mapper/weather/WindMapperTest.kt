package com.rohan.weatherprediction.data.mapper.weather

import com.rohan.weatherprediction.data.repository.TestUtils
import org.junit.Assert
import org.junit.Test

class WindMapperTest{
    private val mapper = WindMapper()

    @Test
    fun map() {
        val windModel = TestUtils.getWindModel()
        val windEntity = TestUtils.getWindEntity()

        val expected = mapper.map(remote = windModel)
        Assert.assertEquals(expected, windEntity)
    }


}
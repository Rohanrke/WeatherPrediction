package com.rohan.weatherprediction.data.mapper.weather

import com.rohan.weatherprediction.data.repository.TestUtils
import org.junit.Assert
import org.junit.Test

class MainDataMapperTest{

    private val mapper = MainDataMapper()

    @Test
    fun map() {
        val model = TestUtils.getMainDataModel()
        val entity = TestUtils.getMainDataEntity()

        val expected = mapper.map(remote = model)
        Assert.assertEquals(expected, entity)
    }
}
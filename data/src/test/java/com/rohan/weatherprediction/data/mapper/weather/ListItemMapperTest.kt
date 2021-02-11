package com.rohan.weatherprediction.data.mapper.weather
import com.rohan.weatherprediction.data.repository.TestUtils
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ListItemMapperTest{

    @MockK
    private lateinit var windMapper: WindMapper

    @MockK
    private lateinit var mainDataMapper: MainDataMapper

    @MockK
    private lateinit var weatherItemMapper: WeatherItemMapper

    private lateinit var mapper: ListItemMapper


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapper = ListItemMapper(windMapper = windMapper, mainDataMapper =  mainDataMapper,weatherItemMapper = weatherItemMapper)
    }

    @Test
    fun map(){
        val entity = TestUtils.getListItemEntity()
        val model = TestUtils.getListItemModel()
        val windEntity = TestUtils.getWindEntity()
        val mainDataEntity = TestUtils.getMainDataEntity()
        val weatherItemEntity = TestUtils.getWeatherItemEntity()

        every { windMapper.map(any()) } returns windEntity
        every { mainDataMapper.map(any()) } returns mainDataEntity
        every { weatherItemMapper.map(any()) } returns weatherItemEntity

        val expected = mapper.map(remote = model)
        Assert.assertEquals(expected, entity)
    }
}
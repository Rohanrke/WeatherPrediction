package com.rohan.weatherprediction.data.mapper.weather

import com.rohan.weatherprediction.data.mapper.RemoteToEntityMapper
import com.rohan.weatherprediction.data.model.ListItemModel
import com.rohan.weatherprediction.domain.entity.*

class ListItemMapper(
    private val windMapper: WindMapper, private val mainDataMapper: MainDataMapper,
    private val weatherItemMapper: WeatherItemMapper
) : RemoteToEntityMapper<ListItemModel, ListItemEntity> {


    override fun map(remote: ListItemModel): ListItemEntity {
        return ListItemEntity(
            dt = remote.dt,
            rain = remote.rain?.let {
                RainEntity(jsonMember3h = it.jsonMember3h)
            },
            dtTxt = remote.dtTxt,
            snowEntity = remote.snowModel?.let {
                SnowEntity(jsonMember3h = it.jsonMember3h)
            },
            weather = remote.weather?.map {
                it?.let { item ->
                    weatherItemMapper.map(item)
                }
            },
            main = remote.main?.let {
                mainDataMapper.map(it)
            },
            clouds = remote.clouds?.all,
            country = remote.sys?.country,
            wind = remote.wind?.let {
                windMapper.map(it)
            }
        )
    }
}
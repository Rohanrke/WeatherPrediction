package com.rohan.weatherprediction.data.mapper.weather

import com.rohan.weatherprediction.data.mapper.RemoteToEntityMapper
import com.rohan.weatherprediction.data.model.ForecastModel
import com.rohan.weatherprediction.domain.entity.ForecastEntity

class ForecastMapper(
    private val cityMapper: CityMapper,
    private val listItemMapper: ListItemMapper
) : RemoteToEntityMapper<ForecastModel, ForecastEntity> {

    override fun map(remote: ForecastModel): ForecastEntity {
        return ForecastEntity(
            city = remote.city?.let {
                cityMapper.map(it)
            },
            cnt = remote.cnt,
            message = remote.message,
            cod = remote.cod,
            list = remote.list?.map {
                listItemMapper.map(it)
            })
    }
}
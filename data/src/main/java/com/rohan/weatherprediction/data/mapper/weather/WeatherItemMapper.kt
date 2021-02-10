package com.rohan.weatherprediction.data.mapper.weather

import com.rohan.weatherprediction.data.mapper.RemoteToEntityMapper
import com.rohan.weatherprediction.data.model.WeatherItemModel
import com.rohan.weatherprediction.domain.entity.WeatherItemEntity

class WeatherItemMapper : RemoteToEntityMapper<WeatherItemModel, WeatherItemEntity> {

    override fun map(remote: WeatherItemModel): WeatherItemEntity {
        return WeatherItemEntity(
            icon = remote.icon, description = remote.description,
            main = remote.main, id = remote.id
        )
    }
}
package com.rohan.weatherprediction.data.mapper.weather

import com.rohan.weatherprediction.data.mapper.RemoteToEntityMapper
import com.rohan.weatherprediction.data.model.WindModel
import com.rohan.weatherprediction.domain.entity.WindEntity

class WindMapper: RemoteToEntityMapper<WindModel, WindEntity> {

    override fun map(remote: WindModel): WindEntity {
        return WindEntity(deg = remote.deg, speed = remote.speed)
    }
}
package com.rohan.weatherprediction.data.mapper.weather

import com.rohan.weatherprediction.data.mapper.RemoteToEntityMapper
import com.rohan.weatherprediction.data.model.MainDataModel
import com.rohan.weatherprediction.domain.entity.MainDataEntity

class MainDataMapper : RemoteToEntityMapper<MainDataModel, MainDataEntity> {

    override fun map(remote: MainDataModel): MainDataEntity {
        return MainDataEntity(
            temp = remote.temp, tempMin = remote.tempMin,
            grndLevel = remote.grndLevel, tempKf = remote.tempKf, tempMax = remote.tempMax,
            humidity = remote.humidity, pressure = remote.pressure, seaLevel = remote.seaLevel
        )
    }
}
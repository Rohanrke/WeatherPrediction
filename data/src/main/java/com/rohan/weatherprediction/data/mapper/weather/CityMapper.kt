package com.rohan.weatherprediction.data.mapper.weather

import com.rohan.weatherprediction.data.mapper.RemoteToEntityMapper
import com.rohan.weatherprediction.data.model.CityModel
import com.rohan.weatherprediction.domain.entity.CityEntity

class CityMapper: RemoteToEntityMapper<CityModel, CityEntity>{

    override fun map(remote: CityModel): CityEntity {
        return CityEntity(country = remote.country,
            lat = remote.coord?.lat, lon = remote.coord?.lon,
            name = remote.name, id = remote.id)
    }
}
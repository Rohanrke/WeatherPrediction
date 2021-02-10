package com.rohan.weatherprediction.data.mapper.weather

import com.rohan.weatherprediction.data.mapper.RemoteToEntityMapper
import com.rohan.weatherprediction.data.model.CurrentWeatherModel
import com.rohan.weatherprediction.domain.entity.CurrentWeatherEntity


class CurrentWeatherMapper(
    private val windMapper: WindMapper, private val mainDataMapper: MainDataMapper,
    private val weatherItemMapper: WeatherItemMapper
) : RemoteToEntityMapper<CurrentWeatherModel, CurrentWeatherEntity> {

    override fun map(remote: CurrentWeatherModel): CurrentWeatherEntity {
        return CurrentWeatherEntity(
            visibility = remote.visibility,
            timezone = remote.timezone,
            main = remote.main?.let {
                mainDataMapper.map(it)
            },
            clouds = remote.clouds?.all,
            country = remote.sys?.country,
            dt = remote.dt,
            lon = remote.coord?.lon,
            lat = remote.coord?.lat,
            weather = remote.weather?.map {
                it?.let { item ->
                    weatherItemMapper.map(item)
                }
            },
            cityName = remote.name,
            cod = remote.cod,
            id = remote.id,
            base = remote.base,
            wind = remote.wind?.let {
                windMapper.map(it)
            },

            )
    }
}
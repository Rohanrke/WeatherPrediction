package com.rohan.weatherprediction.data.repository

import com.rohan.weatherprediction.data.model.*
import com.rohan.weatherprediction.domain.entity.*
import io.mockk.InternalPlatformDsl.toStr

object TestUtils {

    fun getCurrentModel() = CurrentWeatherModel(
        visibility = 3000,
        timezone = 19800,
        id = 1264527,
        name = "Chennai",
        cod = 200,
        dt = 1613016169,
        base = "stations",
        coord = getCoordinateModel(),
        main = getMainDataModel(),
        wind = getWindModel(),
        clouds = getCloudsModel(),
        sys = getSysModel(),
        weather = listOf(getWeatherItemModel())
    )


    fun getWindModel() = WindModel(speed = 1.03, deg = 0.0)

    fun getWindEntity() = WindEntity(speed = 1.03, deg = 0.0)

    fun getRainModel() = RainModel(jsonMember3h = 23.3)

    fun getSnowModel() = SnowModel(jsonMember3h = 23.3)

    fun getRainEntity() = RainEntity(jsonMember3h = 23.3)

    fun getSnowEntity() = SnowEntity(jsonMember3h = 23.3)

    fun getMainDataModel() = MainDataModel(
        temp = 24.0,
        tempMin = 24.0,
        tempMax = 24.0,
        pressure = 1014.0,
        humidity = 78,
        tempKf = 26.94,
        grndLevel = 10.0,
        seaLevel = 10.1
    )

    fun getMainDataEntity() = MainDataEntity(
        temp = 24.0,
        tempMin = 24.0,
        tempMax = 24.0,
        pressure = 1014.0,
        humidity = 78,
        tempKf = 26.94,
        grndLevel = 10.0,
        seaLevel = 10.1
    )

    fun getListItemModel() = ListItemModel(
        dt = 1613034000,
        rain = getRainModel(),
        dtTxt = "123",
        snowModel = getSnowModel(),
        weather = listOf(getWeatherItemModel()),
        main = getMainDataModel(),
        clouds = getCloudsModel(),
        sys = getSysModel(),
        wind = getWindModel()
    )

    fun getListItemEntity() = ListItemEntity(
        dt = 1613034000,
        rain = getRainEntity(),
        dtTxt = "123",
        snowEntity = getSnowEntity(),
        weather= listOf(getWeatherItemEntity()),
        main = getMainDataEntity(),
        clouds = getCloudsModel().all,
        country = getSysModel().country,
        wind = getWindEntity()
    )


    fun getCoordinateModel() = CoordinateModel(lon = 80.2785, lat = 13.0878)

    fun getCloudsModel() = CloudsModel(all = 20)


    fun getCityModel() =
        CityModel(id = 1264527, name = "Chennai", country = "IN", coord = getCoordinateModel())


    fun getSysModel() = SysModel(country = "IN")

    fun getWeatherItemModel() =
        WeatherItemModel(icon = "50d", description = "mist", main = "Mist", id = 701)

    fun getWeatherItemEntity() =
        WeatherItemEntity(icon = "50d", description = "mist", main = "Mist", id = 701)


    fun getCurrentEntity(): CurrentWeatherEntity {
        return CurrentWeatherEntity(
            visibility = 3000,
            timezone = 19800,
            id = 1264527,
            cod = 200,
            dt = 1613016169,
            base = "stations",
            lon = 80.2785,
            lat = 13.0878,
            clouds = 20,
            country = "IN",
            cityName = "Chennai",
            weather = listOf(getWeatherItemEntity()),
            wind = getWindEntity()
        )
    }

    fun getForecastModel() = ForecastModel(
        cod = "200", message = 0.0, cnt = 40,
        city = getCityModel(), list = listOf(getListItemModel())
    )
}
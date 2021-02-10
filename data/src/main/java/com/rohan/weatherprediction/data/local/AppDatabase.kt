package com.rohan.weatherprediction.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rohan.weatherprediction.data.model.CurrentWeatherModel
import com.rohan.weatherprediction.data.model.ForecastModel


private const val DB_NAME = "weather_prediction.db"

@Database(
    entities = [CurrentWeatherModel::class, ForecastModel::class],
    exportSchema = false,
    version = 1
)

@TypeConverters(value = [WeatherItemTypeConverters::class, ListItemTypeConverters::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun currentDao(): CurrentWeatherDao
    abstract fun forecastDao(): ForecastWeatherDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, DB_NAME
        ).build()
    }
}

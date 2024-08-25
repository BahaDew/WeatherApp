package uz.bahadew.weatherapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.bahadew.weatherapp.data.source.local.dao.WeatherDao
import uz.bahadew.weatherapp.data.source.local.entity.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getWeatherDao() : WeatherDao
}
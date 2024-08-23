package uz.bahadew.weatherapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.bahadew.weatherapp.data.source.local.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM WeatherEntity")
    fun getAllWeather() : List<WeatherEntity>


}
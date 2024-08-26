package uz.bahadew.weatherapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import uz.bahadew.weatherapp.data.source.local.entity.WeatherEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM WeatherEntity")
    fun getAllWeather(): List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weatherEntity: WeatherEntity): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateWeather(weatherEntity: WeatherEntity)

    @Delete
    fun deleteWeather(weatherEntity: WeatherEntity)

    @Query("DELETE FROM WeatherEntity")
    fun deleteAllWeather()
}
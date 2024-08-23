package uz.bahadew.weatherapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val region : String
)

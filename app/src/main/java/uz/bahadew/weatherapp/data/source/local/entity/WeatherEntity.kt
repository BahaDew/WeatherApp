package uz.bahadew.weatherapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,
    val region : String,
    val temp : Double,
    val windSpeed : Float,
    val country : String,
    val humidity : Int,
    val sunrise : Long,
    val sunset : Long,
    val timezone : Long,
    val weatherMain: String,
    val weatherDescription: String,
    val dataTime : Long
)
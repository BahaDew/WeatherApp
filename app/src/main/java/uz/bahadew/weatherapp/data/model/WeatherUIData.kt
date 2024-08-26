package uz.bahadew.weatherapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherUIData(
    val region : String,
    val temp : Double,
    val windSpeed : Float,
    val country : String,
    val humidity : Int,
    val sunrise : Long,
    val sunset : Long,
    val id : Long,
    val timezone : Long,
    val weatherMain: String,
    val weatherDescription: String
) : Parcelable

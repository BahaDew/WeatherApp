package uz.bahadew.weatherapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val cord: Cord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Int
)

data class Cord(
    val lon: Double,
    val lat: Double
)

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
)

data class Main(
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("grnd_level")
    val grndLevel: Int
)

data class Wind(
    val speed: Float,
    val deg: Int
)

data class Clouds(
    val all: Int,
)

data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrice: Long,
    val sunset: Long
)
/*
{
    "coord": {
    "lon": 71.7975,
    "lat": 40.3732
},
    "weather": [
    {
        "id": 800,
        "main": "Clear",
        "description": "clear sky",
        "icon": "01d"
    }
    ],
    "base": "stations",
     "main": {
     "temp": 302.17,
     "feels_like": 300.84,
     "temp_min": 302.17,
     "temp_max": 302.17,
     "pressure": 1009,
     "humidity": 26,
     "sea_level": 1009,
     "grnd_level": 947
    },
    "visibility": 10000,
    "wind": {
    "speed": 4.12,
    "deg": 300
     },
    "clouds": {
    "all": 0
    },
    "dt": 1724567230,
    "sys": {
     "type": 1,
     "id": 9014,
     "country": "UZ",
     "sunrise": 1724546021,
     "sunset": 1724594189
    },
    "timezone": 18000,
    "id": 1514019,
    "name": "Fergana",
    "cod": 200
}
*/

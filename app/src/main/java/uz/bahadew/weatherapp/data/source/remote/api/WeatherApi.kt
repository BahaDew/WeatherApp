package uz.bahadew.weatherapp.data.source.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.bahadew.weatherapp.data.source.remote.response.WeatherResponse

interface WeatherApi {

    // https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid=f23600911b1d116d8c6af59a946218f9"
    @GET("data/2.5/weather")
    suspend fun getWeatherByLatLon(
        @Query(value = "lan")
        lat: Double,
        @Query(value = "lon")
        lon: Double,
        @Query(value = "appid")
        appid: String
    ) : Response<WeatherResponse>
}
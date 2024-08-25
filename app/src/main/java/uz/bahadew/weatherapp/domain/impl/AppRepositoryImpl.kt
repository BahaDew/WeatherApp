package uz.bahadew.weatherapp.domain.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import uz.bahadew.weatherapp.data.model.WeatherUIData
import uz.bahadew.weatherapp.data.source.remote.api.WeatherApi
import uz.bahadew.weatherapp.data.source.remote.response.Cord
import uz.bahadew.weatherapp.data.source.remote.response.ErrorResponse
import uz.bahadew.weatherapp.data.source.remote.response.toWeatherUIData
import uz.bahadew.weatherapp.domain.AppRepository
import uz.bahadew.weatherapp.utils.bahaLogger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : AppRepository {

    private val apiKey = "f23600911b1d116d8c6af59a946218f9"

    private val gson = Gson()

    private val regionsCord = arrayListOf(
        // Farg'ona
        Cord(lat = 40.37317079450999, lon = 71.7974996160707),
        // Andijon
        Cord(lat = 40.81498164980603, lon = 72.27479605110557),
        // Oltiariq
        Cord(lat = 40.40224554466811, lon = 71.48702396841807),
        // Toshkent
        Cord(lat = 41.29950542257913, lon = 69.24364551061205),
        // Namangan
        Cord(lat = 41.007150026619534, lon = 71.63774039904057),
        // Qo'qon
        Cord(lat = 40.534528081484964, lon = 70.9331836448178),
    )

    override fun getAllRegionWeather(): Flow<Result<List<WeatherUIData>>> =
        callbackFlow<Result<List<WeatherUIData>>> {
            val list = ArrayList<WeatherUIData>()
            for (i in 0..<regionsCord.size) {
                val response = weatherApi.getWeatherByLatLon(
                    lat = regionsCord[i].lat,
                    lon = regionsCord[i].lon,
                    appid = apiKey
                )
                val body = response.body()
                val errorBody = response.errorBody()
                if (body != null && response.isSuccessful) {
                    list.add(body.toWeatherUIData())
                    if (i == regionsCord.size - 1) {
                        trySend(Result.success(list))
                    }
                } else if (errorBody != null) {
                    val errorMessage =
                        gson.fromJson(errorBody.string(), ErrorResponse::class.java).message
                    trySend(Result.failure(Throwable(errorMessage)))
                } else {
                    trySend(Result.failure(Throwable("Nomalum xatolik!")))
                }
            }
            awaitClose()
        }.flowOn(Dispatchers.IO).catch { emit(Result.failure(Throwable("Nomalum xatolik!"))) }
}
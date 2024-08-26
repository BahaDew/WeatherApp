package uz.bahadew.weatherapp.domain

import kotlinx.coroutines.flow.Flow
import uz.bahadew.weatherapp.data.model.WeatherUIData

interface AppRepository {
    fun getAllRegionWeather(): Flow<Result<List<WeatherUIData>>>
    fun searchRegion(name: String): Flow<Result<List<WeatherUIData>>>
}
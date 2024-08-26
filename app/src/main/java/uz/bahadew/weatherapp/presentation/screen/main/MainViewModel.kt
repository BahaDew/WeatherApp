package uz.bahadew.weatherapp.presentation.screen.main

import kotlinx.coroutines.flow.Flow
import uz.bahadew.weatherapp.data.model.WeatherUIData

interface MainViewModel {
     val allRegionWeather : Flow<List<WeatherUIData>>
     val progressState : Flow<Boolean>
     val warningDialogMessage : Flow<String>

     fun requestAllRegionWeather()

     fun onClickItem(weatherUIData: WeatherUIData)
     fun searchRegion(name: String)
}
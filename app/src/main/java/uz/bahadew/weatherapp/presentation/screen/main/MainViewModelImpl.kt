package uz.bahadew.weatherapp.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.bahadew.weatherapp.data.model.WeatherUIData
import uz.bahadew.weatherapp.domain.AppRepository
import uz.bahadew.weatherapp.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val appRepository: AppRepository,
    private val appNavigator: AppNavigator
) : ViewModel(), MainViewModel {
    override val allRegionWeather = MutableSharedFlow<List<WeatherUIData>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    override val progressState = MutableStateFlow(false)
    private var _warningDialogMessage: ((message: String) -> Unit)? = null
    override val warningDialogMessage = channelFlow {
        _warningDialogMessage = { message ->
            if (message == "Internet") {
                trySend("No Internet connection found. Check your connection or try again")
            } else {
                trySend("Something went wrong. Please try again !")
            }
        }
        awaitClose { _warningDialogMessage = null }
    }

    override fun requestAllRegionWeather() {
        progressState.value = true
        appRepository
            .getAllRegionWeather()
            .onEach { result ->
                progressState.value = false
                result.onSuccess { list ->
                    allRegionWeather.emit(list)
                }.onFailure { error ->
                    _warningDialogMessage?.invoke(error.message.toString())
                }
            }.launchIn(viewModelScope)
    }

    override fun onClickItem(weatherUIData: WeatherUIData) {
        viewModelScope.launch {
            appNavigator.navigateTo(MainScreenDirections.actionMainScreenToInfoScreen(weaterUIData = weatherUIData))
        }
    }

    override fun searchRegion(name: String) {
        appRepository
            .searchRegion(name = name)
            .onEach { result ->
                result.onSuccess { list ->
                    allRegionWeather.emit(list)
                }.onFailure { message ->
                    _warningDialogMessage?.invoke(message.message.toString())
                }
            }.launchIn(viewModelScope)
    }
}
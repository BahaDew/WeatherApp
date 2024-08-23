package uz.bahadew.weatherapp.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.bahadew.weatherapp.domain.AppRepository
import uz.bahadew.weatherapp.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator,
    private val appRepository: AppRepository
) : SplashViewModel, ViewModel() {
    override fun toMainScreen() {
        viewModelScope.launch {
            appNavigator.navigateTo(SplashScreenDirections.actionSplashScreenToMainScreen())
        }
    }

}
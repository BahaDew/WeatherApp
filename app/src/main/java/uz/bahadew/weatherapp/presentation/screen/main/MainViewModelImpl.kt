package uz.bahadew.weatherapp.presentation.screen.main

import androidx.lifecycle.ViewModel
import uz.bahadew.weatherapp.domain.AppRepository
import uz.bahadew.weatherapp.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModelImpl @Inject constructor(
    private val appRepository: AppRepository,
    private val appNavigator: AppNavigator
) : ViewModel(), MainViewModel {

}
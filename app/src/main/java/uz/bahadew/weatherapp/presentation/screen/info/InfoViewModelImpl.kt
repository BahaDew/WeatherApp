package uz.bahadew.weatherapp.presentation.screen.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.bahadew.weatherapp.domain.AppRepository
import uz.bahadew.weatherapp.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class InfoViewModelImpl @Inject constructor(
    private val appRepository: AppRepository,
    private val appNavigator: AppNavigator
) : ViewModel(), InfoViewModel {
    override fun onClickBack() {
        viewModelScope.launch {
            appNavigator.navigateUp()
        }
    }

}
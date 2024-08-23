package uz.bahadew.weatherapp.navigation

import androidx.navigation.NavDirections

interface AppNavigator {
    suspend fun navigateTo(direction: NavDirections)
    suspend fun navigateUp()
    suspend fun popBackStack()
}
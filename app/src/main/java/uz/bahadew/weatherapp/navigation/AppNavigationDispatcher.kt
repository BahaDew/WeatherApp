package uz.bahadew.weatherapp.navigation

import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigationDispatcher @Inject constructor() : AppNavigationHandler, AppNavigator {
    override val buffer = MutableSharedFlow<AppNavigation>()

    private suspend fun navigate(appNavigation: AppNavigation) {
        buffer.emit(appNavigation)
    }

    override suspend fun navigateTo(direction: NavDirections) = navigate {
        this.navigate(direction)
    }

    override suspend fun navigateUp() = navigate {
        this.navigateUp()
    }

    override suspend fun popBackStack() = navigate {
        this.popBackStack()
    }
}
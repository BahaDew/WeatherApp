package uz.bahadew.weatherapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bahadew.weatherapp.navigation.AppNavigationDispatcher
import uz.bahadew.weatherapp.navigation.AppNavigationHandler
import uz.bahadew.weatherapp.navigation.AppNavigator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @[Binds Singleton]
    fun bindAppNavigationHandler(impl: AppNavigationDispatcher): AppNavigationHandler

    @[Binds Singleton]
    fun bindAppNavigator(impl: AppNavigationDispatcher): AppNavigator
}
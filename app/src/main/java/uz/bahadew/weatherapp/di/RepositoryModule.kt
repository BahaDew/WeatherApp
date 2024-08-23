package uz.bahadew.weatherapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.bahadew.weatherapp.domain.AppRepository
import uz.bahadew.weatherapp.domain.impl.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindAppRepository(impl : AppRepositoryImpl) : AppRepository
}
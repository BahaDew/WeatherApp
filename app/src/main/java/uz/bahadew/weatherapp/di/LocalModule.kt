package uz.bahadew.weatherapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.bahadew.weatherapp.data.source.local.WeatherDatabase
import uz.bahadew.weatherapp.data.source.local.dao.WeatherDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @[Provides Singleton]
    fun provideRoomDatabase(@ApplicationContext context: Context) : WeatherDatabase {
        return Room
            .databaseBuilder(
                context = context,
                WeatherDatabase::class.java,
                "BAHA_WEATHER"
            )
            .build()
    }

    @[Provides Singleton]
    fun provideWeatherDao(weatherDatabase: WeatherDatabase) : WeatherDao {
        return weatherDatabase.getWeatherDao()
    }
}
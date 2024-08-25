package uz.bahadew.weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.bahadew.weatherapp.data.source.remote.api.WeatherApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    // https://api.openweathermap.org/data/2.5/weather?lat=40.37317079450999&lon=71.7974996160707&appid=f23600911b1d116d8c6af59a946218f9
    @[Provides Singleton]
    fun provideRetrofit() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @[Provides Singleton]
    fun provideWeatherApi(retrofit: Retrofit) : WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
}
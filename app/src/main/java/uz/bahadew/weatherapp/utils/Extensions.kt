package uz.bahadew.weatherapp.utils

import timber.log.Timber
import uz.bahadew.weatherapp.data.model.WeatherUIData
import uz.bahadew.weatherapp.data.source.local.entity.WeatherEntity
import uz.bahadew.weatherapp.data.source.remote.response.WeatherResponse

fun bahaLogger(message: String, tag: String = "BAHA") {
    Timber.tag(tag).d(message)
}

fun WeatherResponse.toWeatherUIData(): WeatherUIData {
    return WeatherUIData(
        region = name,
        temp = main.temp,
        windSpeed = wind.speed,
        country = sys.country,
        humidity = main.humidity,
        sunrise = sys.sunrise,
        sunset = sys.sunset,
        timezone = timezone,
        weatherMain = weather[0].main,
        weatherDescription = weather[0].description,
        dataTime = dt
    )
}

fun WeatherResponse.toWeatherEntityData(): WeatherEntity {
    return WeatherEntity(
        region = name,
        temp = main.temp,
        windSpeed = wind.speed,
        country = sys.country,
        humidity = main.humidity,
        sunrise = sys.sunrise,
        sunset = sys.sunset,
        timezone = timezone,
        weatherMain = weather[0].main,
        weatherDescription = weather[0].description,
        dataTime = dt
    )
}

fun WeatherEntity.toWeatherUIData(): WeatherUIData {
    return WeatherUIData(
        region = region,
        temp = temp,
        windSpeed = windSpeed,
        country = country,
        humidity = humidity,
        sunrise = sunrise,
        sunset = sunset,
        timezone = timezone,
        weatherMain = weatherMain,
        weatherDescription = weatherDescription,
        dataTime = dataTime
    )
}
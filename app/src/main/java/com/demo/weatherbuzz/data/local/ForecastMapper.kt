package com.demo.weatherbuzz.data.local

import com.demo.weatherbuzz.data.remote.dto.ForecastResponse

fun ForecastResponse.toEntities(): List<ForecastEntity> {
    val cityName = city.name
    val country = city.country
    val currentTime = System.currentTimeMillis()

    return list.map { item ->
        ForecastEntity(
            cityName = cityName,
            country = country,
            dateTime = item.dateTime,
            temperature = item.main.temp,
            feelsLike = item.main.feelsLike,
            tempMin = item.main.tempMin,
            tempMax = item.main.tempMax,
            humidity = item.main.humidity,
            pressure = item.main.pressure,
            weatherMain = item.weather.firstOrNull()?.main ?: "",
            weatherDescription = item.weather.firstOrNull()?.description ?: "",
            weatherIcon = item.weather.firstOrNull()?.icon ?: "",
            windSpeed = item.wind.speed,
            cloudiness = item.clouds.all,
            dateTimeText = item.dateTimeText,
            lastUpdated = currentTime
        )
    }
}

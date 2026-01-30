package com.demo.weatherbuzz.ui

import com.demo.weatherbuzz.data.local.ForecastEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class WeatherUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val cityName: String = "",
    val country: String = "",
    val currentWeather: DayForecast? = null,
    val forecasts: List<DayForecast> = emptyList(),
    val error: String? = null,
    val useCelsius: Boolean = true,
    val isDarkTheme: Boolean = false,
    val lastUpdated: Long = 0L
) {
    val weatherCondition: String?
        get() = currentWeather?.weatherMain

    val lastUpdatedText: String
        get() = if (lastUpdated > 0) formatLastUpdated(lastUpdated) else ""
}

data class DayForecast(
    val dayLabel: String,
    val date: String,
    val tempCelsius: Double,
    val tempMin: Double,
    val tempMax: Double,
    val feelsLike: Double,
    val weatherMain: String,
    val weatherDescription: String,
    val weatherIcon: String,
    val humidity: Int,
    val windSpeed: Double
)

fun List<ForecastEntity>.toDayForecasts(): List<DayForecast> {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val displayFormat = SimpleDateFormat("EEE, MMM d", Locale.getDefault())

    return this
        .groupBy { dateFormat.format(Date(it.dateTime * 1000)) }
        .entries
        .take(3)
        .mapIndexed { index, (_, forecasts) ->
            val middayForecast = forecasts.minByOrNull {
                kotlin.math.abs(getHourOfDay(it.dateTime) - 12)
            } ?: forecasts.first()

            val dayLabel = when (index) {
                0 -> "Today"
                1 -> "Tomorrow"
                else -> SimpleDateFormat("EEEE", Locale.getDefault())
                    .format(Date(middayForecast.dateTime * 1000))
            }

            DayForecast(
                dayLabel = dayLabel,
                date = displayFormat.format(Date(middayForecast.dateTime * 1000)),
                tempCelsius = middayForecast.temperature,
                tempMin = forecasts.minOf { it.tempMin },
                tempMax = forecasts.maxOf { it.tempMax },
                feelsLike = middayForecast.feelsLike,
                weatherMain = middayForecast.weatherMain,
                weatherDescription = middayForecast.weatherDescription,
                weatherIcon = middayForecast.weatherIcon,
                humidity = middayForecast.humidity,
                windSpeed = middayForecast.windSpeed
            )
        }
}

private fun getHourOfDay(timestamp: Long): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timestamp * 1000
    return calendar.get(Calendar.HOUR_OF_DAY)
}

fun formatTemperature(tempCelsius: Double, useCelsius: Boolean): String {
    val temp = if (useCelsius) tempCelsius else (tempCelsius * 9 / 5) + 32
    val unit = if (useCelsius) "°C" else "°F"
    return "${temp.toInt()}$unit"
}

fun formatLastUpdated(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp

    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60

    return when {
        seconds < 60 -> "Updated just now"
        minutes < 60 -> "Updated ${minutes}m ago"
        hours < 24 -> "Updated ${hours}h ago"
        else -> {
            val format = SimpleDateFormat("MMM d, h:mm a", Locale.getDefault())
            "Updated ${format.format(Date(timestamp))}"
        }
    }
}

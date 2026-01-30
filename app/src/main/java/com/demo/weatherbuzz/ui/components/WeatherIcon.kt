package com.demo.weatherbuzz.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.demo.weatherbuzz.R

@Composable
fun WeatherIcon(
    weatherMain: String,
    modifier: Modifier = Modifier,
    size: Dp = 120.dp
) {
    val animationRes = when (weatherMain.lowercase()) {
        "clear" -> R.raw.weather_clear
        "clouds" -> R.raw.weather_mist
        "rain", "drizzle" -> R.raw.weather_rain
        "thunderstorm" -> R.raw.weather_thunderstorm
        "snow" -> R.raw.weather_snow
        "mist", "fog", "haze" -> R.raw.weather_mist
        else -> R.raw.weather_clear
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationRes))

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(size)
        )
    }
}

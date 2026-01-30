package com.demo.weatherbuzz.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

enum class WeatherTheme {
    SUNNY,
    CLOUDY,
    RAINY,
    STORMY,
    SNOWY,
    DEFAULT
}

private val SunnyLightScheme = lightColorScheme(
    primary = Color(0xFFFF9800),
    onPrimary = Color.White,
    secondary = Color(0xFFFFB74D),
    onSecondary = Color(0xFF3E2723),
    tertiary = Color(0xFFFFD54F),
    background = Color(0xFFFFF8E1),
    surface = Color(0xFFFFFFFF),
    onBackground = Color(0xFF3E2723),
    onSurface = Color(0xFF3E2723),
    primaryContainer = Color(0xFFFFE0B2),
    onPrimaryContainer = Color(0xFF5D4037),
    surfaceVariant = Color(0xFFFFF3E0),
    onSurfaceVariant = Color(0xFF5D4037),
    error = Color(0xFFE53935)
)

private val SunnyDarkScheme = darkColorScheme(
    primary = Color(0xFFFFB74D),
    onPrimary = Color(0xFF3E2723),
    secondary = Color(0xFFFFCC80),
    onSecondary = Color(0xFF3E2723),
    tertiary = Color(0xFFFFE082),
    background = Color(0xFF1A1200),
    surface = Color(0xFF2D2000),
    onBackground = Color(0xFFFFF8E1),
    onSurface = Color(0xFFFFF8E1),
    primaryContainer = Color(0xFF5D4037),
    onPrimaryContainer = Color(0xFFFFE0B2),
    surfaceVariant = Color(0xFF3E2C1C),
    onSurfaceVariant = Color(0xFFFFCC80),
    error = Color(0xFFEF5350)
)

private val CloudyLightScheme = lightColorScheme(
    primary = Color(0xFF78909C),
    onPrimary = Color.White,
    secondary = Color(0xFF90A4AE),
    onSecondary = Color(0xFF263238),
    tertiary = Color(0xFFB0BEC5),
    background = Color(0xFFECEFF1),
    surface = Color(0xFFFFFFFF),
    onBackground = Color(0xFF263238),
    onSurface = Color(0xFF263238),
    primaryContainer = Color(0xFFCFD8DC),
    onPrimaryContainer = Color(0xFF37474F),
    surfaceVariant = Color(0xFFE0E3E5),
    onSurfaceVariant = Color(0xFF455A64),
    error = Color(0xFFE53935)
)

private val CloudyDarkScheme = darkColorScheme(
    primary = Color(0xFF90A4AE),
    onPrimary = Color(0xFF263238),
    secondary = Color(0xFFB0BEC5),
    onSecondary = Color(0xFF263238),
    tertiary = Color(0xFFCFD8DC),
    background = Color(0xFF121517),
    surface = Color(0xFF1E2326),
    onBackground = Color(0xFFECEFF1),
    onSurface = Color(0xFFECEFF1),
    primaryContainer = Color(0xFF37474F),
    onPrimaryContainer = Color(0xFFCFD8DC),
    surfaceVariant = Color(0xFF2C3338),
    onSurfaceVariant = Color(0xFFB0BEC5),
    error = Color(0xFFEF5350)
)

private val RainyLightScheme = lightColorScheme(
    primary = Color(0xFF1976D2),
    onPrimary = Color.White,
    secondary = Color(0xFF42A5F5),
    onSecondary = Color.White,
    tertiary = Color(0xFF64B5F6),
    background = Color(0xFFE3F2FD),
    surface = Color(0xFFFFFFFF),
    onBackground = Color(0xFF0D47A1),
    onSurface = Color(0xFF0D47A1),
    primaryContainer = Color(0xFFBBDEFB),
    onPrimaryContainer = Color(0xFF0D47A1),
    surfaceVariant = Color(0xFFD6E8F5),
    onSurfaceVariant = Color(0xFF1565C0),
    error = Color(0xFFE53935)
)

private val RainyDarkScheme = darkColorScheme(
    primary = Color(0xFF64B5F6),
    onPrimary = Color(0xFF0D47A1),
    secondary = Color(0xFF90CAF9),
    onSecondary = Color(0xFF0D47A1),
    tertiary = Color(0xFFBBDEFB),
    background = Color(0xFF051525),
    surface = Color(0xFF0A1F35),
    onBackground = Color(0xFFE3F2FD),
    onSurface = Color(0xFFE3F2FD),
    primaryContainer = Color(0xFF1565C0),
    onPrimaryContainer = Color(0xFFBBDEFB),
    surfaceVariant = Color(0xFF102840),
    onSurfaceVariant = Color(0xFF90CAF9),
    error = Color(0xFFEF5350)
)

private val StormyLightScheme = lightColorScheme(
    primary = Color(0xFF5E35B1),
    onPrimary = Color.White,
    secondary = Color(0xFF7E57C2),
    onSecondary = Color.White,
    tertiary = Color(0xFF9575CD),
    background = Color(0xFFEDE7F6),
    surface = Color(0xFFFFFFFF),
    onBackground = Color(0xFF311B92),
    onSurface = Color(0xFF311B92),
    primaryContainer = Color(0xFFD1C4E9),
    onPrimaryContainer = Color(0xFF311B92),
    surfaceVariant = Color(0xFFE0D6F0),
    onSurfaceVariant = Color(0xFF4527A0),
    error = Color(0xFFE53935)
)

private val StormyDarkScheme = darkColorScheme(
    primary = Color(0xFFB39DDB),
    onPrimary = Color(0xFF311B92),
    secondary = Color(0xFFCE93D8),
    onSecondary = Color(0xFF311B92),
    tertiary = Color(0xFFE1BEE7),
    background = Color(0xFF0D0520),
    surface = Color(0xFF1A0A30),
    onBackground = Color(0xFFEDE7F6),
    onSurface = Color(0xFFEDE7F6),
    primaryContainer = Color(0xFF4527A0),
    onPrimaryContainer = Color(0xFFD1C4E9),
    surfaceVariant = Color(0xFF251540),
    onSurfaceVariant = Color(0xFFCE93D8),
    error = Color(0xFFEF5350)
)

private val SnowyLightScheme = lightColorScheme(
    primary = Color(0xFF00ACC1),
    onPrimary = Color.White,
    secondary = Color(0xFF26C6DA),
    onSecondary = Color(0xFF004D40),
    tertiary = Color(0xFF4DD0E1),
    background = Color(0xFFE0F7FA),
    surface = Color(0xFFFFFFFF),
    onBackground = Color(0xFF006064),
    onSurface = Color(0xFF006064),
    primaryContainer = Color(0xFFB2EBF2),
    onPrimaryContainer = Color(0xFF00363A),
    surfaceVariant = Color(0xFFD4F0F4),
    onSurfaceVariant = Color(0xFF00838F),
    error = Color(0xFFE53935)
)

private val SnowyDarkScheme = darkColorScheme(
    primary = Color(0xFF4DD0E1),
    onPrimary = Color(0xFF00363A),
    secondary = Color(0xFF80DEEA),
    onSecondary = Color(0xFF00363A),
    tertiary = Color(0xFFB2EBF2),
    background = Color(0xFF001518),
    surface = Color(0xFF002025),
    onBackground = Color(0xFFE0F7FA),
    onSurface = Color(0xFFE0F7FA),
    primaryContainer = Color(0xFF00838F),
    onPrimaryContainer = Color(0xFFB2EBF2),
    surfaceVariant = Color(0xFF003840),
    onSurfaceVariant = Color(0xFF80DEEA),
    error = Color(0xFFEF5350)
)

private val DefaultLightScheme = lightColorScheme(
    primary = Color(0xFF2196F3),
    onPrimary = Color.White,
    secondary = Color(0xFF03A9F4),
    onSecondary = Color.White,
    tertiary = Color(0xFF00BCD4),
    background = Color(0xFFF5F5F5),
    surface = Color(0xFFFFFFFF),
    onBackground = Color(0xFF212121),
    onSurface = Color(0xFF212121),
    primaryContainer = Color(0xFFBBDEFB),
    onPrimaryContainer = Color(0xFF0D47A1),
    surfaceVariant = Color(0xFFE8E8E8),
    onSurfaceVariant = Color(0xFF424242),
    error = Color(0xFFE53935)
)

private val DefaultDarkScheme = darkColorScheme(
    primary = Color(0xFF64B5F6),
    onPrimary = Color(0xFF0D47A1),
    secondary = Color(0xFF4FC3F7),
    onSecondary = Color(0xFF01579B),
    tertiary = Color(0xFF4DD0E1),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onBackground = Color(0xFFE0E0E0),
    onSurface = Color(0xFFE0E0E0),
    primaryContainer = Color(0xFF1565C0),
    onPrimaryContainer = Color(0xFFBBDEFB),
    surfaceVariant = Color(0xFF2C2C2C),
    onSurfaceVariant = Color(0xFFB0BEC5),
    error = Color(0xFFEF5350)
)

fun getWeatherTheme(weatherMain: String?): WeatherTheme {
    return when (weatherMain?.lowercase()) {
        "clear" -> WeatherTheme.SUNNY
        "clouds" -> WeatherTheme.CLOUDY
        "rain", "drizzle" -> WeatherTheme.RAINY
        "thunderstorm" -> WeatherTheme.STORMY
        "snow" -> WeatherTheme.SNOWY
        "mist", "fog", "haze" -> WeatherTheme.CLOUDY
        else -> WeatherTheme.DEFAULT
    }
}

private fun getColorSchemes(theme: WeatherTheme): Pair<androidx.compose.material3.ColorScheme, androidx.compose.material3.ColorScheme> {
    return when (theme) {
        WeatherTheme.SUNNY -> Pair(SunnyLightScheme, SunnyDarkScheme)
        WeatherTheme.CLOUDY -> Pair(CloudyLightScheme, CloudyDarkScheme)
        WeatherTheme.RAINY -> Pair(RainyLightScheme, RainyDarkScheme)
        WeatherTheme.STORMY -> Pair(StormyLightScheme, StormyDarkScheme)
        WeatherTheme.SNOWY -> Pair(SnowyLightScheme, SnowyDarkScheme)
        WeatherTheme.DEFAULT -> Pair(DefaultLightScheme, DefaultDarkScheme)
    }
}

@Composable
fun WeatherBuzzTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    weatherCondition: String? = null,
    content: @Composable () -> Unit
) {
    val weatherTheme = getWeatherTheme(weatherCondition)
    val (lightScheme, darkScheme) = getColorSchemes(weatherTheme)

    val colorScheme = if (darkTheme) darkScheme else lightScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

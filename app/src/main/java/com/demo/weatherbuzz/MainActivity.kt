package com.demo.weatherbuzz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demo.weatherbuzz.ui.WeatherScreen
import com.demo.weatherbuzz.ui.WeatherViewModel
import com.demo.weatherbuzz.ui.theme.WeatherBuzzTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        
        var keepSplash = true
        splashScreen.setKeepOnScreenCondition { keepSplash }
        
        enableEdgeToEdge()

        setContent {
            val viewModel: WeatherViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            keepSplash = uiState.isLoading && uiState.currentWeather == null

            WeatherBuzzTheme(
                darkTheme = uiState.isDarkTheme,
                weatherCondition = uiState.weatherCondition
            ) {
                WeatherScreen(viewModel = viewModel)
            }
        }
    }
}

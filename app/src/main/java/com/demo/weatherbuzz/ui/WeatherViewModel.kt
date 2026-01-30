package com.demo.weatherbuzz.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.weatherbuzz.data.ForecastResult
import com.demo.weatherbuzz.data.WeatherRepository
import com.demo.weatherbuzz.data.local.ForecastEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private var currentCity = ""

    init {
        loadLastCity()
    }

    private fun loadLastCity() {
        viewModelScope.launch {
            repository.getLastSearchedCity()?.let { city ->
                searchCity(city)
            }
        }
    }

    fun searchCity(city: String) {
        if (city.isBlank()) return
        currentCity = city

        viewModelScope.launch {
            repository.getForecast(city).collect { result ->
                when (result) {
                    is ForecastResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true, error = null) }
                    }
                    is ForecastResult.Cached -> {
                        updateWithData(result.data, keepRefreshing = true)
                    }
                    is ForecastResult.Fresh -> {
                        updateWithData(result.data, keepRefreshing = false)
                    }
                    is ForecastResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isRefreshing = false,
                                error = if (result.cached != null) 
                                    "Couldn't refresh: ${result.message}" 
                                else 
                                    result.message
                            )
                        }
                    }
                }
            }
        }
    }

    private fun updateWithData(data: List<ForecastEntity>, keepRefreshing: Boolean) {
        val forecasts = data.toDayForecasts()
        val current = forecasts.firstOrNull()
        val cityName = data.firstOrNull()?.cityName ?: currentCity
        val country = data.firstOrNull()?.country ?: ""
        val lastUpdated = data.firstOrNull()?.lastUpdated ?: 0L

        _uiState.update {
            it.copy(
                isLoading = false,
                isRefreshing = if (keepRefreshing) it.isRefreshing else false,
                cityName = cityName,
                country = country,
                currentWeather = current,
                forecasts = forecasts,
                error = null,
                lastUpdated = lastUpdated
            )
        }
    }

    fun refresh() {
        if (currentCity.isNotBlank()) {
            _uiState.update { it.copy(isRefreshing = true) }
            searchCity(currentCity)
        }
    }

    fun toggleTemperatureUnit() {
        _uiState.update { it.copy(useCelsius = !it.useCelsius) }
    }

    fun toggleTheme() {
        _uiState.update { it.copy(isDarkTheme = !it.isDarkTheme) }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

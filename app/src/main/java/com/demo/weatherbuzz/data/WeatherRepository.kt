package com.demo.weatherbuzz.data

import com.demo.weatherbuzz.core.network.NetworkResult
import com.demo.weatherbuzz.core.network.safeApiCall
import com.demo.weatherbuzz.data.local.ForecastEntity
import com.demo.weatherbuzz.data.local.WeatherDao
import com.demo.weatherbuzz.data.local.toEntities
import com.demo.weatherbuzz.data.remote.WeatherApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

sealed class ForecastResult {
    data class Cached(val data: List<ForecastEntity>) : ForecastResult()
    data class Fresh(val data: List<ForecastEntity>) : ForecastResult()
    data class Error(val message: String, val cached: List<ForecastEntity>?) : ForecastResult()
    object Loading : ForecastResult()
}

class WeatherRepository(
    private val apiService: WeatherApiService,
    private val weatherDao: WeatherDao
) {

    fun getForecast(city: String): Flow<ForecastResult> = flow {
        emit(ForecastResult.Loading)

        val cached = weatherDao.getAllForecastsOnce()
        val cachedCity = cached.firstOrNull()?.cityName
        val isSameCity = cachedCity.equals(city, ignoreCase = true)
        
        val validCache = if (cached.isNotEmpty() && isSameCity) cached else null
        
        if (validCache != null) {
            emit(ForecastResult.Cached(validCache))
        }

        when (val result = safeApiCall { apiService.getForecast(city) }) {
            is NetworkResult.Success -> {
                val entities = result.data.toEntities()
                weatherDao.deleteAll()
                weatherDao.insertAll(entities)
                emit(ForecastResult.Fresh(entities))
            }
            is NetworkResult.Error -> {
                emit(ForecastResult.Error(result.message, validCache))
            }
            is NetworkResult.Loading -> {}
        }
    }

    suspend fun getLastSearchedCity(): String? {
        return weatherDao.getLastSearchedCity()
    }
}

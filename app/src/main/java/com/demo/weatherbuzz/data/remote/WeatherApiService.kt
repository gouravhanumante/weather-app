package com.demo.weatherbuzz.data.remote

import com.demo.weatherbuzz.BuildConfig
import com.demo.weatherbuzz.data.remote.dto.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): Response<ForecastResponse>
}

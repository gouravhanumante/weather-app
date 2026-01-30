package com.demo.weatherbuzz.di

import androidx.room.Room
import com.demo.weatherbuzz.core.network.ApiClient
import com.demo.weatherbuzz.data.WeatherRepository
import com.demo.weatherbuzz.data.local.WeatherDatabase
import com.demo.weatherbuzz.data.remote.WeatherApiService
import com.demo.weatherbuzz.ui.WeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { ApiClient.retrofit }

    single { ApiClient.create<WeatherApiService>() }

    single {
        Room.databaseBuilder(
            androidContext(),
            WeatherDatabase::class.java,
            "weather_database"
        ).build()
    }

    single { get<WeatherDatabase>().weatherDao() }

    single { WeatherRepository(get(), get()) }

    viewModel { WeatherViewModel(get()) }
}

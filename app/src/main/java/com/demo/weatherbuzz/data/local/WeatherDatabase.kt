package com.demo.weatherbuzz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ForecastEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}

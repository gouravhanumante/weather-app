package com.demo.weatherbuzz.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM forecast ORDER BY dateTime ASC")
    fun getAllForecasts(): Flow<List<ForecastEntity>>

    @Query("SELECT * FROM forecast ORDER BY dateTime ASC")
    suspend fun getAllForecastsOnce(): List<ForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(forecasts: List<ForecastEntity>)

    @Query("DELETE FROM forecast")
    suspend fun deleteAll()

    @Query("SELECT cityName FROM forecast LIMIT 1")
    suspend fun getLastSearchedCity(): String?
}

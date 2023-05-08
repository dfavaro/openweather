package com.danielefavaro.openweather.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danielefavaro.openweather.core.database.model.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherModelDao {
    @Query("SELECT * FROM weatherentity ORDER BY uid")
    fun getCities(): Flow<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(items: List<WeatherEntity>)

    @Query("SELECT * FROM weatherentity WHERE uid=:id")
    suspend fun getCity(id: Int): WeatherEntity
}

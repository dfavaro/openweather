package com.danielefavaro.openweather.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danielefavaro.openweather.core.database.dao.WeatherModelDao
import com.danielefavaro.openweather.core.database.model.WeatherEntity

@Database(
    entities = [
        WeatherEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherModelDao(): WeatherModelDao
}

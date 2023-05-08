package com.danielefavaro.openweather.core.database.di

import android.content.Context
import androidx.room.Room
import com.danielefavaro.openweather.core.database.AppDatabase
import com.danielefavaro.openweather.core.database.dao.WeatherModelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideWeatherModelDao(appDatabase: AppDatabase): WeatherModelDao {
        return appDatabase.weatherModelDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "OpenWeather"
        ).build()
    }
}

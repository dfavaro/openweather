package com.danielefavaro.openweather.core.data.di

import com.danielefavaro.openweather.core.data.WeatherRepositoryImpl
import com.danielefavaro.openweather.core.data.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsWeatherModelRepository(
        weatherModelRepository: WeatherRepositoryImpl
    ): WeatherRepository
}

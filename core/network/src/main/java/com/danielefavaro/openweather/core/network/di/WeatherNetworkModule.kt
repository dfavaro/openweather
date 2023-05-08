package com.danielefavaro.openweather.core.network.di

import com.danielefavaro.openweather.core.network.api.OpenWeatherService
import com.danielefavaro.openweather.core.network.domain.WeatherDataSource
import com.danielefavaro.openweather.core.network.source.WeatherDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object WeatherNetworkModule {

    @Singleton
    @Provides
    fun provideWeatherRemoteService(retrofit: Retrofit): OpenWeatherService =
        retrofit.create(OpenWeatherService::class.java)

    @Singleton
    @Provides
    fun provideWeatherRemoteSource(
        dispatcher: CoroutineDispatcher,
        openWeatherService: OpenWeatherService
    ): WeatherDataSource = WeatherDataSourceImpl(
        dispatcher = dispatcher,
        openWeatherService = openWeatherService,
    )
}

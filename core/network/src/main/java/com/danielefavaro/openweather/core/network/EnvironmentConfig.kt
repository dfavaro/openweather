package com.danielefavaro.openweather.core.network

import okhttp3.logging.HttpLoggingInterceptor

interface EnvironmentConfig {
    val url: String
    val apiKey: String
    val logLevel: HttpLoggingInterceptor.Level
}

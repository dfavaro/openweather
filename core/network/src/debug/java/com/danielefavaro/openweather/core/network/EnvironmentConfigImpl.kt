package com.danielefavaro.openweather.core.network

import android.content.Context
import javax.inject.Inject
import okhttp3.logging.HttpLoggingInterceptor

class EnvironmentConfigImpl @Inject constructor(
    context: Context
) : EnvironmentConfig {
    override val url: String = "https://api.openweathermap.org/data/2.5/"
    override val apiKey: String = context.getString(R.string.openweather_api_key)
    override val logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
}

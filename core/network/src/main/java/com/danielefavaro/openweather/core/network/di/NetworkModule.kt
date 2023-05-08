package com.danielefavaro.openweather.core.network.di

import android.content.Context
import com.danielefavaro.openweather.core.network.EnvironmentConfig
import com.danielefavaro.openweather.core.network.EnvironmentConfigImpl
import com.danielefavaro.openweather.core.network.helper.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Qualifier
annotation class ApiAuthInterceptor

@Qualifier
annotation class LoggingInterceptor

private const val API_KEY = "apikey"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context) = ConnectivityManager(
        context = context
    )

    @Singleton
    @Provides
    fun provideEnvironmentConfig(@ApplicationContext context: Context): EnvironmentConfig =
        EnvironmentConfigImpl(
            context = context
        )

    @LoggingInterceptor
    @Singleton
    @Provides
    fun provideLoggingInterceptor(environmentConfig: EnvironmentConfig): Interceptor =
        HttpLoggingInterceptor().setLevel(environmentConfig.logLevel)

    @ApiAuthInterceptor
    @Singleton
    @Provides
    fun provideApiAuthInterceptor(environmentConfig: EnvironmentConfig): Interceptor =
        Interceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(API_KEY, environmentConfig.apiKey)
                .build()

            val requestBuilder: Request.Builder = original.newBuilder().url(url)

            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }

    @Singleton
    @Provides
    fun provideAuthOkHttpClient(
        @ApiAuthInterceptor apiAuthInterceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(apiAuthInterceptor)
        addInterceptor(loggingInterceptor)

        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
        connectTimeout(30, TimeUnit.SECONDS)
    }.build()

    @Singleton
    @Provides
    fun provideRetrofitClient(
        environmentConfig: EnvironmentConfig,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(environmentConfig.url)
        .build()
}

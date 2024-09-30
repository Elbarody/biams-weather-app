package com.elbarody.baimsweatherapp.di

import com.elbarody.data.remote.Constants
import com.elbarody.data.remote.Constants.CITIES_BASE_URL
import com.elbarody.data.remote.api.CitiesApi
import com.elbarody.data.remote.api.ForecastApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideCitiesApiService(okHttpClient: OkHttpClient): CitiesApi {
        return Retrofit.Builder()
            .baseUrl(CITIES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CitiesApi::class.java)
    }

    @Provides
    fun provideWeatherApiService(okHttpClient: OkHttpClient): ForecastApi {
        return Retrofit.Builder()
            .baseUrl(Constants.WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ForecastApi::class.java)
    }
}

package com.elbarody.baimsweatherapp.di

import com.elbarody.data.remote.datasource.forecastremotedatasource.ForecastDataSource
import com.elbarody.data.remote.datasource.forecastremotedatasource.IForecastDataSource
import com.elbarody.data.repository.forecastrepo.ForecastRepository
import com.elbarody.domain.repository.forecastrepo.IForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ForecastDataModule {
    @Binds
    abstract fun bindForecastRepository(forecastRepository: ForecastRepository): IForecastRepository

    @Binds
    abstract fun bindForecastDataSource(forecastRepository: ForecastDataSource): IForecastDataSource
}
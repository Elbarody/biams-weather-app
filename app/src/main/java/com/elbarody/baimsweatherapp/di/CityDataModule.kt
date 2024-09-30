package com.elbarody.baimsweatherapp.di

import com.elbarody.data.remote.datasource.citydatasource.CityDataSource
import com.elbarody.data.remote.datasource.citydatasource.ICityDataSource
import com.elbarody.data.repository.cityrepo.CityRepository
import com.elbarody.domain.repository.cityrepo.ICityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CityDataModule {
    @Binds
    abstract fun bindCityRepository(citeRepository: CityRepository): ICityRepository

    @Binds
    abstract fun bindCityDataSource(citeRepository: CityDataSource): ICityDataSource
}
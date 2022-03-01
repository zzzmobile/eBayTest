package com.test.ebay.di

import androidx.lifecycle.ViewModelProvider
import com.test.ebay.data.ApiClient
import com.test.ebay.data.EarthquakeRemoteDataSource
import com.test.ebay.model.EarthquakeDataSource
import com.test.ebay.model.EarthquakeRepository
import com.test.ebay.viewmodel.ViewModelFactory

object Injection {
    private val dataSource: EarthquakeDataSource = EarthquakeRemoteDataSource(ApiClient)
    private val repository = EarthquakeRepository(dataSource)
    private val viewModelFactory = ViewModelFactory(repository)

    fun providerRepository(): EarthquakeDataSource {
        return dataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return viewModelFactory
    }
}
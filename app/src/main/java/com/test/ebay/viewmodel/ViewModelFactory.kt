package com.test.ebay.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.ebay.model.EarthquakeRepository

class ViewModelFactory(private val listRepository: EarthquakeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EarthquakeListViewModel(listRepository) as T
    }
}
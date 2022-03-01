package com.test.ebay.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.ebay.data.FetchEarthquakesCallback
import com.test.ebay.model.EarthquakeRepository
import com.test.ebay.model.Earthquakes

class EarthquakeListViewModel(private val listRepository: EarthquakeRepository) : ViewModel() {

    private val _earthquakes = MutableLiveData<Earthquakes>()
    val earthquakes: LiveData<Earthquakes> = _earthquakes

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError


    fun loadData(formatted: Boolean, north: Double, south: Double, east: Double, west: Double, username: String) {
        _isViewLoading.value = true
        listRepository.fetchEarthquakes(formatted, north, south, east, west, username, object : FetchEarthquakesCallback<Earthquakes> {
            override fun onSuccess(data: Earthquakes?) {
                _isViewLoading.postValue(false)
                _earthquakes.postValue(data)
            }

            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue(error)
            }
        })
    }
}
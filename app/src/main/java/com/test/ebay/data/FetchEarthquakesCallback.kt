package com.test.ebay.data

interface FetchEarthquakesCallback<T> {
    fun onSuccess(data: T?)
    fun onError(error: String?)
}
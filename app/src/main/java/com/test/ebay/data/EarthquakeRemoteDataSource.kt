package com.test.ebay.data

import com.test.ebay.model.EarthquakeDataSource
import com.test.ebay.model.Earthquakes
import io.reactivex.rxjava3.core.Observable

class EarthquakeRemoteDataSource(apiClient: ApiClient) : EarthquakeDataSource {
    private lateinit var fetchEarthquakesCall: Observable<Earthquakes>
    private val service = apiClient.build()

    override fun retrieveEarthquakes(formatted: Boolean, north: Double, south: Double, east: Double, west: Double, username: String, callback: FetchEarthquakesCallback<Earthquakes>) {
        fetchEarthquakesCall = service!!.loadData(formatted, north, south, east, west, username)
        fetchEarthquakesCall.subscribe(
            { value -> callback.onSuccess(value) },
            { error -> callback.onError(error.message) }
        )
    }
}
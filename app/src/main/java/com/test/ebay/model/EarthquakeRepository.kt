package com.test.ebay.model

import com.test.ebay.data.FetchEarthquakesCallback

class EarthquakeRepository(private val earthquakesDataSource: EarthquakeDataSource) {
    fun fetchEarthquakes(formatted: Boolean, north: Double, south: Double, east: Double, west: Double, username: String, callback: FetchEarthquakesCallback<Earthquakes>) {
        earthquakesDataSource.retrieveEarthquakes(formatted, north, south, east, west, username, callback)
    }
}
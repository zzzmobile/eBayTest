package com.test.ebay.model

import com.test.ebay.data.FetchEarthquakesCallback

interface EarthquakeDataSource {
    fun retrieveEarthquakes(formatted: Boolean, north: Double, south: Double, east: Double, west: Double, username: String, callback: FetchEarthquakesCallback<Earthquakes>)
}
package com.test.ebay.model

import com.google.gson.annotations.SerializedName

data class Earthquakes (
    @SerializedName("earthquakes")
    var earthQuakes: List<Earthquake>? = listOf()
)
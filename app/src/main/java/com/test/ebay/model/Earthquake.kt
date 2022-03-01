package com.test.ebay.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Earthquake(
    @SerializedName("datetime")
    var datetime: String? = null,
    @SerializedName("depth")
    var depth: Double = 0.0,
    @SerializedName("lng")
    var longitude: Double = 0.0,
    @SerializedName("lat")
    var latitude: Double = 0.0,
    @SerializedName("src")
    var source: String? = null,
    @SerializedName("magnitude")
    var magnitude: Double = 0.0,
    @SerializedName("eqid")
    var eqid: String? = null
) : Serializable
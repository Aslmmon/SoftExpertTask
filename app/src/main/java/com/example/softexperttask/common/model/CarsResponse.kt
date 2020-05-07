package com.example.softexperttask.common.model


import com.google.gson.annotations.SerializedName

data class CarsResponse(
    @SerializedName("data")
    var `data`: List<Data>?,
    @SerializedName("status")
    var status: Int?
)
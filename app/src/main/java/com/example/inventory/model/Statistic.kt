package com.example.inventory.model

import com.google.gson.annotations.SerializedName

data class Statistic (
    @SerializedName("profit") val profit: Double,
    @SerializedName("qty") val qty: Int,
)

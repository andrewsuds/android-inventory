package com.example.inventory.model

import com.google.gson.annotations.SerializedName

data class Delete (
    @SerializedName("productid") val productID: Int,
    @SerializedName("message") val message: String,
)
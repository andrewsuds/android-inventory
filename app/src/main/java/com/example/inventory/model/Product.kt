package com.example.inventory.model

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("productid") val productID: Int,
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: Double,
    @SerializedName("qtyonhand") val qtyOnHand: Int,
    @SerializedName("message") val message: String
    )
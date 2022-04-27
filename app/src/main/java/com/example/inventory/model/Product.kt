package com.example.inventory.model

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("productID") val productID: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sellPrice") val sellPrice: Double,
    @SerializedName("value") val value: Double,
    @SerializedName("qtyOnHand") val qtyOnHand: Int,
    )
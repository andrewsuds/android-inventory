package com.example.inventory.model

import com.google.gson.annotations.SerializedName

data class BuyReceipt (
    @SerializedName("buyreceiptid") val buyReceiptID: Int,
    @SerializedName("buyprice") val buyPrice: Double,
    @SerializedName("buytotal") val buyTotal: Double,
    @SerializedName("qty") val qty: Int,
    @SerializedName("date") val date: String,
    @SerializedName("productid") val productID: Int,
    @SerializedName("name") val name: String,
    @SerializedName("message") val message: String
)
package com.example.inventory.model

import com.google.gson.annotations.SerializedName

data class SellReceipt (
    @SerializedName("sellreceiptid") val sellReceiptID: Int,
    @SerializedName("sellprice") val sellPrice: Double,
    @SerializedName("buyprice") val buyPrice: Double,
    @SerializedName("selltotal") val sellTotal: Double,
    @SerializedName("buytotal") val buyTotal: Double,
    @SerializedName("profit") val profit: Double,
    @SerializedName("qty") val qty: Int,
    @SerializedName("date") val date: String,
    @SerializedName("productid") val productID: Int,
    @SerializedName("message") val message: String
)
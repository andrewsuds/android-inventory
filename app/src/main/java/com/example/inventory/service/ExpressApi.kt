package com.example.inventory.service

import com.example.inventory.model.BuyReceipt
import com.example.inventory.model.Product
import com.example.inventory.model.SellReceipt
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ExpressApi {

    @Headers("Content-Type: application/json")
    @POST("product")
    fun addProductData(@Body product: Product): Call<Product>

    @Headers("Content-Type: application/json")
    @POST("buy")
    fun addBuyReceipt(@Body buyReceipt: BuyReceipt): Call<BuyReceipt>

    @Headers("Content-Type: application/json")
    @POST("sell")
    fun addSellReceipt(@Body sellReceipt: SellReceipt): Call<SellReceipt>

    @GET("all")
    fun getProducts(): Call<List<Product>>



}
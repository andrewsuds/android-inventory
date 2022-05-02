package com.example.inventory.service

import com.example.inventory.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

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

    @GET("allwithstock")
    fun getProductsWithStock(): Call<List<Product>>

    @GET("allbuy")
    fun getBuyReceipts(): Call<List<BuyReceipt>>

    @GET("allsell")
    fun getSellReceipts(): Call<List<SellReceipt>>

    @GET("statistic")
    fun getStatistic(): Call<Statistic>

    @Headers("Content-Type: application/json")
    @POST("delproduct")
    fun deleteProduct(@Body delete: Delete): Call<Delete>

}
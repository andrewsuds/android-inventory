package com.example.inventory

import com.example.inventory.model.Product
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

    @GET("all")
    suspend fun getAll(): Response<List<Product>>

    @GET("one")
    suspend fun getOne(): Product

}
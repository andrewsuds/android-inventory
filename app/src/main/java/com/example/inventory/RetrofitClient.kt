package com.example.inventory

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://10.0.0.204:3001/")
        .client(okHttpClient)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

}
package com.example.inventory.repository

import com.example.inventory.RetrofitClient
import com.example.inventory.model.Product
import retrofit2.Response

class Repository {

    suspend fun getOne(): Product {
        return RetrofitClient.api.getOne()
    }

    suspend fun getAll(): Response<List<Product>> {
        return RetrofitClient.api.getAll()
    }
}
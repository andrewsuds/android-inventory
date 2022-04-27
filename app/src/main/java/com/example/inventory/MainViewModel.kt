package com.example.inventory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.model.Product
import com.example.inventory.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    val myResponse : MutableLiveData<Product> = MutableLiveData()
    val myResponse2 : MutableLiveData<Response<List<Product>>> = MutableLiveData()

    fun getOne() {
        viewModelScope.launch {
            val response = repository.getOne()
            myResponse.value = response
        }
    }

    fun getAll() {
        viewModelScope.launch {
            val response = repository.getAll()
            myResponse2.value = response
        }
    }
}
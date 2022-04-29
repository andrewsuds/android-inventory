package com.example.inventory.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.example.inventory.R
import com.example.inventory.model.Product
import com.example.inventory.service.RetrofitClient
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductActivity : AppCompatActivity() {

    val retrofit = RetrofitClient.api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)

        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val name = findViewById<TextInputEditText>(R.id.outlinedTextField1)

        topAppBar.setNavigationOnClickListener {
            finish()
        }

        topAppBar.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.save -> {
                    val addProduct = Product(0, name.text.toString(), 0.00, 0, "")
                    retrofit.addProductData(addProduct).enqueue(object : Callback<Product>{
                        override fun onResponse(call: Call<Product>, response: Response<Product>) {
                            if(response.body()?.productID != 0) {
                                Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
                                finish()
                            } else {
                                Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<Product>, t: Throwable) {
                            Log.e("andrew", "Error with submitting Product data")
                            Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }

            true
        }
    }


}
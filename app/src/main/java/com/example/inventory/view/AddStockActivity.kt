package com.example.inventory.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.inventory.R
import com.example.inventory.model.BuyReceipt
import com.example.inventory.model.Product
import com.example.inventory.service.RetrofitClient
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddStockActivity : AppCompatActivity() {

    val retrofit = RetrofitClient.api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addstock)

        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val buyTotal = findViewById<TextInputEditText>(R.id.outlinedTextField1)
        val qty = findViewById<TextInputEditText>(R.id.outlinedTextField2)

        var nameList = mutableListOf<String>("Select Product");
        var idList = mutableListOf<Int>(0);

        var spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, nameList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        retrofit.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val products: List<Product> = response.body()!!

                for (product in products) {
                    nameList.add(product.name)
                    idList.add(product.productID)
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        topAppBar.setNavigationOnClickListener {
            createDialog()
        }

        topAppBar.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.save -> {
                    val addBuyReceipt = BuyReceipt(0,0.00,buyTotal.text.toString().toDouble(),qty.text.toString().toInt(),"",idList[spinner.selectedItemPosition],"","")
                    retrofit.addBuyReceipt(addBuyReceipt).enqueue(object : Callback<BuyReceipt>{
                        override fun onResponse(call: Call<BuyReceipt>, response: Response<BuyReceipt>) {
                            if(response.body()?.buyReceiptID != 0) {
                                Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
                                finish()
                            } else {
                                Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<BuyReceipt>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }

            true
        }
    }

    private fun createDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Discard draft?")
            .setPositiveButton("Discard") { dialog, which ->
                finish()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                //nothing
            }
            .show()
    }


}
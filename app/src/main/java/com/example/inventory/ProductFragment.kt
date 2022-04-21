package com.example.inventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.inventory.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product, container, false)
        val textView1 = view.findViewById<TextView>(R.id.textView)
        val textView2 = view.findViewById<TextView>(R.id.textView2)
        val inputName = view.findViewById<EditText>(R.id.editTextTextPersonName)
        val inputValue = view.findViewById<EditText>(R.id.editTextTextPersonName2)
        val button = view.findViewById<Button>(R.id.button)

        // Retrofit Builder
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.0.0.204:3001/")
            .build()

        val expressApi = retrofitBuilder.create(ExpressApi::class.java)

        // Get Data
        fun getProductDataHere() {
            val getCall: Call<List<Product>> = expressApi.getProductData()
            getCall.enqueue(object: Callback<List<Product>>{
                override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                    val products:List<Product> = response.body()!!

                    val stringBuilder = StringBuilder()

                    for(product in products) {
                        stringBuilder.append("${product.productID}\n")
                        stringBuilder.append("${product.name}\n")
                        stringBuilder.append("${product.sellPrice}\n")
                        stringBuilder.append("${product.value}\n")
                        stringBuilder.append("${product.qtyOnHand}\n\n")
                    }

                    textView1.text = stringBuilder
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    textView1.text = t.message.toString()
                }
            })
        }

        getProductDataHere()

        // Data Send
        button.setOnClickListener {
            val addProduct = Product(null, inputName.text.toString(),inputValue.text.toString().toDouble(), null, null)
            val call = expressApi.addProductData(addProduct)
            call.enqueue(object : Callback<Product>{
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    textView2.text = response.body()?.name.toString()
                    getProductDataHere()
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    textView2.text = t.message.toString()
                }
            })
        }



        return view
    }

}
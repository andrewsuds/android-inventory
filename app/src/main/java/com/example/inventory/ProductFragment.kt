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
import java.lang.StringBuilder

class ProductFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product, container, false)
        val textView1 = view.findViewById<TextView>(R.id.textView)

        val retrofit = RetrofitClient.buildService(ExpressApi::class.java)

        retrofit.getProductData().enqueue(object : Callback<List<Product>>{
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

        return view
    }

}
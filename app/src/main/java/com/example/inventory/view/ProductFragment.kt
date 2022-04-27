package com.example.inventory.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.R
import com.example.inventory.adapter.ProductAdapter
import com.example.inventory.model.Product
import com.example.inventory.service.RetrofitClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent as Intent

class ProductFragment : Fragment() {

    private val productAdapter by lazy { ProductAdapter() }
    private val retrofit = RetrofitClient.api

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val productFAB = view.findViewById<FloatingActionButton>(R.id.productFAB)
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        setupRecycler()



        productFAB.setOnClickListener {
            //myDialog.show(parentFragmentManager, "custom dialog")
            val intent = Intent(activity, MainActivity2::class.java)

            startActivity(intent)

        }

        return view
    }

    private fun setupRecycler() {
        retrofit.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val products: List<Product> = response.body()!!
                productAdapter.setData(products)

            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onResume() {
        super.onResume()
        setupRecycler()
    }


}
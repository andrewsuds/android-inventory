package com.example.inventory.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.R
import com.example.inventory.adapter.SellAdapter
import com.example.inventory.model.SellReceipt
import com.example.inventory.service.RetrofitClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SellFragment : Fragment() {

    private val sellAdapter by lazy { SellAdapter() }
    private val retrofit = RetrofitClient.api

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sell, container, false)

        val sellFAB = view.findViewById<FloatingActionButton>(R.id.sellFAB)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = sellAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        setupRecycler()

        sellFAB.setOnClickListener {
            val intent = Intent(activity, SellStockActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun setupRecycler() {
        retrofit.getSellReceipts().enqueue(object : Callback<List<SellReceipt>> {
            override fun onResponse(call: Call<List<SellReceipt>>, response: Response<List<SellReceipt>>) {
                val sellReceipts: List<SellReceipt> = response.body()!!
                sellAdapter.setData(sellReceipts)

            }

            override fun onFailure(call: Call<List<SellReceipt>>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        setupRecycler()
    }

}
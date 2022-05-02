package com.example.inventory.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.R
import com.example.inventory.adapter.BuyAdapter
import com.example.inventory.model.BuyReceipt
import com.example.inventory.service.RetrofitClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuyFragment : Fragment() {

    private val buyAdapter by lazy { BuyAdapter(requireContext()) }
    private val retrofit = RetrofitClient.api

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_buy, container, false)

        val buyFAB = view.findViewById<FloatingActionButton>(R.id.buyFAB)
        val loading = view.findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = buyAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        buyAdapter.getBuyReceipts(loading)

        buyFAB.setOnClickListener {
            val intent = Intent(activity, AddStockActivity::class.java)
            startActivity(intent)
        }

        return view
    }



    override fun onResume() {
        super.onResume()
        buyAdapter.getBuyReceipts(null)
    }

}
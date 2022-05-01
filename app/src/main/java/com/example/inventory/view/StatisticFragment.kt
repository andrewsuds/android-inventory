package com.example.inventory.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.inventory.R
import com.example.inventory.model.BuyReceipt
import com.example.inventory.model.Statistic
import com.example.inventory.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StatisticFragment : Fragment() {

    private val retrofit = RetrofitClient.api

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_statistic, container, false)
        val profit = view.findViewById<TextView>(R.id.textView4)
        val qty = view.findViewById<TextView>(R.id.textView5)

        retrofit.getStatistic().enqueue(object : Callback<Statistic> {
            override fun onResponse(call: Call<Statistic>, response: Response<Statistic>) {
                val statistics = response.body()!!

                profit.text = "$${"%.2f".format(statistics.profit)}"
                qty.text = "${statistics.qty}"
            }

            override fun onFailure(call: Call<Statistic>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })

        return view
    }

}
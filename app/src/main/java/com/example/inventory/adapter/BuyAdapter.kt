package com.example.inventory.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.R
import com.example.inventory.model.BuyReceipt
import com.example.inventory.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuyAdapter(val myContext: Context): RecyclerView.Adapter<BuyAdapter.MyViewHolder>() {

    private var myList = emptyList<BuyReceipt>()
    private val retrofit = RetrofitClient.api

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_buy, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val textView1 = holder.itemView.findViewById<TextView>(R.id.rowTextView1)
        val textView2 = holder.itemView.findViewById<TextView>(R.id.rowTextView2)
        val textView3 = holder.itemView.findViewById<TextView>(R.id.rowTextView3)
        val textView4 = holder.itemView.findViewById<TextView>(R.id.rowTextView4)

        textView1.text = myList[position].name
        textView2.text = myList[position].date
        textView3.text = "Quantity Added:  ${myList[position].qty}"
        textView4.text = "Paid:  $${"%.2f".format(myList[position].buyTotal)} or $${"%.2f".format(myList[position].buyPrice)} / item"
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData(newList: List<BuyReceipt>) {
        myList = newList
        notifyDataSetChanged()
    }

    fun getBuyReceipts(loading : ProgressBar?) {
        retrofit.getBuyReceipts().enqueue(object : Callback<List<BuyReceipt>> {
            override fun onResponse(call: Call<List<BuyReceipt>>, response: Response<List<BuyReceipt>>) {
                val buyReceipts: List<BuyReceipt> = response.body()!!
                setData(buyReceipts)
                if (loading != null) {
                    loading.visibility = View.GONE
                }

            }

            override fun onFailure(call: Call<List<BuyReceipt>>, t: Throwable) {
                Toast.makeText(myContext, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }


}
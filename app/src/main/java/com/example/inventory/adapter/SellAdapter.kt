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
import com.example.inventory.model.SellReceipt
import com.example.inventory.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SellAdapter(val myContext : Context): RecyclerView.Adapter<SellAdapter.MyViewHolder>() {

    private var myList = emptyList<SellReceipt>()
    private val retrofit = RetrofitClient.api

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_sell, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val textView1 = holder.itemView.findViewById<TextView>(R.id.rowTextView1)
        val textView2 = holder.itemView.findViewById<TextView>(R.id.rowTextView2)
        val textView3 = holder.itemView.findViewById<TextView>(R.id.rowTextView3)
        val textView4 = holder.itemView.findViewById<TextView>(R.id.rowTextView4)
        val textView5 = holder.itemView.findViewById<TextView>(R.id.rowTextView5)

        textView1.text = myList[position].name
        textView2.text = myList[position].date
        textView3.text = "Quantity Sold: ${myList[position].qty}"
        textView4.text = "Sold:  $${"%.2f".format(myList[position].sellTotal)} or $${"%.2f".format(myList[position].sellPrice)} / item"
        textView5.text = "Profit:  $${"%.2f".format(myList[position].profit)}"
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData(newList: List<SellReceipt>) {
        myList = newList
        notifyDataSetChanged()
    }

    fun getSellReceipts(loading : ProgressBar?) {
        retrofit.getSellReceipts().enqueue(object : Callback<List<SellReceipt>> {
            override fun onResponse(call: Call<List<SellReceipt>>, response: Response<List<SellReceipt>>) {
                val sellReceipts: List<SellReceipt> = response.body()!!
                setData(sellReceipts)
                if (loading != null) {
                    loading.visibility = View.GONE
                }

            }

            override fun onFailure(call: Call<List<SellReceipt>>, t: Throwable) {
                Toast.makeText(myContext, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }


}
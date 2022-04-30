package com.example.inventory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.R
import com.example.inventory.model.BuyReceipt

class BuyAdapter: RecyclerView.Adapter<BuyAdapter.MyViewHolder>() {

    private var myList = emptyList<BuyReceipt>()

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


}
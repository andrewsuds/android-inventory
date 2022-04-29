package com.example.inventory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.R
import com.example.inventory.model.Product

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private var myList = emptyList<Product>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val textView1 = holder.itemView.findViewById<TextView>(R.id.rowTextView1)
        val textView2 = holder.itemView.findViewById<TextView>(R.id.rowTextView2)
        val textView3 = holder.itemView.findViewById<TextView>(R.id.rowTextView3)
        textView1.text = myList[position].productID.toString()
        textView2.text = myList[position].name
        textView3.text = "$${myList[position].value.toString()}"
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData(newList: List<Product>) {
        myList = newList
        notifyDataSetChanged()
    }


}
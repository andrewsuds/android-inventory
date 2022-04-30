package com.example.inventory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.R
import com.example.inventory.model.Product
import java.math.RoundingMode
import java.text.DecimalFormat

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private var myList = emptyList<Product>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_product,parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val textView1 = holder.itemView.findViewById<TextView>(R.id.rowTextView1)
        val textView2 = holder.itemView.findViewById<TextView>(R.id.rowTextView2)
        val textView3 = holder.itemView.findViewById<TextView>(R.id.rowTextView3)

        val averageUnitPrice =
            if (myList[position].qtyOnHand > 0) {
                myList[position].value / myList[position].qtyOnHand
            } else {
                0.0
            }

        textView1.text = myList[position].name
        textView2.text = "Quantity:  ${myList[position].qtyOnHand}"
        textView3.text = "Value:  $${"%.2f".format(myList[position].value)} or $${"%.2f".format(averageUnitPrice)} / item"
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData(newList: List<Product>) {
        myList = newList
        notifyDataSetChanged()
    }


}
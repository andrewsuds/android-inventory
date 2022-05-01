package com.example.inventory.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.R
import com.example.inventory.model.Delete
import com.example.inventory.model.Product
import com.example.inventory.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.RoundingMode
import java.text.DecimalFormat

class ProductAdapter(val myContext: Context) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private var myList = mutableListOf<Product>()
    private val retrofit = RetrofitClient.api

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            var button = itemView.findViewById<Button>(R.id.delete)

            button.setOnClickListener {
                deleteData(myList[adapterPosition].productID, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.card_product, parent, false))
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
        textView3.text =
            "Value:  $${"%.2f".format(myList[position].value)} or $${"%.2f".format(averageUnitPrice)} / item"
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData(newList: List<Product>) {
        myList = newList as MutableList<Product>
        notifyDataSetChanged()
    }

    fun updateData(position: Int) {
        myList.removeAt(position)
        notifyDataSetChanged()
    }

    private fun deleteData(productID : Int, position: Int) {
        val delete = Delete(productID, "")
        retrofit.deleteProduct(delete).enqueue(object: Callback<Delete>{
            override fun onResponse(call: Call<Delete>, response: Response<Delete>) {
                val message = response.body()!!.message
                Toast.makeText(myContext, message, Toast.LENGTH_SHORT).show()

                updateData(position)
            }

            override fun onFailure(call: Call<Delete>, t: Throwable) {
                Toast.makeText(myContext, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }



}
package com.example.inventory.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inventory.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SellFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sell, container, false)

        val sellFAB = view.findViewById<FloatingActionButton>(R.id.sellFAB)

        sellFAB.setOnClickListener {
            val intent = Intent(activity, SellStockActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}
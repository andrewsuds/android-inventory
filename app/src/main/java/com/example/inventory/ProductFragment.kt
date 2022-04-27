package com.example.inventory

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.adapter.MyAdapter
import com.example.inventory.model.Product
import com.example.inventory.repository.Repository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class ProductFragment : Fragment() {

    private lateinit var  viewModel : MainViewModel
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val productFAB = view.findViewById<FloatingActionButton>(R.id.productFAB)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getAll()
        viewModel.myResponse2.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful) {
                response.body()?.let { myAdapter.setData(it) }
            } else {
                // ERROR
            }
        })

        productFAB.setOnClickListener {
            var dialog = CustomDialogFragment()
            dialog.show(parentFragmentManager, "custom dialog")
        }




        return view
    }

}
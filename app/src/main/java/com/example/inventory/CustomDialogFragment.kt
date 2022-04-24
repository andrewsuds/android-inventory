package com.example.inventory

import android.content.ClipData
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.inventory.model.Product
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.elevation.SurfaceColors
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CustomDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_custom_dialog, container, false)
        val name = rootView.findViewById<TextInputEditText>(R.id.outlinedTextField1)
        val value = rootView.findViewById<TextInputEditText>(R.id.outlinedTextField2)
        val topAppBar = rootView.findViewById<MaterialToolbar>(R.id.topAppBar)
        var responseValue : String = "Failure"
        // Retrofit Builder
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.0.0.204:3001/")
            .build()

        val expressApi = retrofitBuilder.create(ExpressApi::class.java)

        fun sendProductData(): String {
            val addProduct = Product(null, name.text.toString(),value.text.toString().toDouble(), null, null)
            val call = expressApi.addProductData(addProduct)

            call.enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    responseValue = response.body()?.name.toString()

                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    print("Failure")
                }
            })

            return responseValue
        }

        //topAppBar.inflateMenu(R.menu.top_app_bar)
        topAppBar.setOnMenuItemClickListener {
            item: MenuItem? ->
            when(item!!.itemId) {
                R.id.save -> {

                    var testData = sendProductData()

                    Toast.makeText(activity, testData, Toast.LENGTH_SHORT).show()

                }
            }

            true
        }


        topAppBar.setNavigationOnClickListener {
            dismiss()
        }


        return rootView
    }






    override fun getTheme(): Int {
        return R.style.DialogTheme
    }
}
package com.example.inventory

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.NonCancellable.cancel

class BuyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_buy, container, false)

        val textView1 = view.findViewById<TextView>(R.id.textView1)

        textView1.setOnClickListener {
            // build alert dialog
            var dialog = CustomDialogFragment()

            dialog.show(parentFragmentManager, "custom dialog")
        }

        return view
    }

}
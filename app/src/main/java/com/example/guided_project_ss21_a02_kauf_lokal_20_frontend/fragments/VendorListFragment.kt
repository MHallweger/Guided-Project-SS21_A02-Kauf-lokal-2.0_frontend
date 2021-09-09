package com.example.guided_project_ss21_a02_kauf_lokal_20_frontend.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.guided_project_ss21_a02_kauf_lokal_20_frontend.R
import com.example.guided_project_ss21_a02_kauf_lokal_20_frontend.adapter.VendorListRecyclerViewAdapter
import com.example.guided_project_ss21_a02_kauf_lokal_20_frontend.model.Vendor
import com.google.gson.Gson

/**
 * A fragment representing a list of Items.
 */
class VendorListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vendor_list, container, false) as RecyclerView

        (activity as AppCompatActivity).supportActionBar?.title = "Händler in deiner Nähe"
        with(view) {
            layoutManager = LinearLayoutManager(context)
            adapter = VendorListRecyclerViewAdapter(listOf<Vendor>())
        }
        addVendorsToAdapter(view)

        return view
    }

    // TODO Maybe extract into connection class
    fun addVendorsToAdapter(view: RecyclerView) {
        val context = view.context
        val adapter = view.adapter as VendorListRecyclerViewAdapter

        val gson = Gson()

        val url = "http://10.0.2.2:8080/vendor"
        val queue = Volley.newRequestQueue(context)
        val vendors = mutableListOf<Vendor>()

        val request = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                for (i in 0 until response.length()) {
                    val vendor = gson.fromJson(response.getJSONObject(i).toString(), Vendor::class.java)
                    vendors.add(vendor)
                }
                adapter.setValues(vendors)
            },
            { error ->
                // TODO Add meaningful error handling
                Log.e("Response", error.message ?: "Keine Fehlermeldung vorhanden")
            }
            )
        queue.add(request)
    }
}
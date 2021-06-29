package com.example.guided_project_ss21_a02_kauf_lokal_20_frontend.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guided_project_ss21_a02_kauf_lokal_20_frontend.adapter.NewsfeedRecyclerViewAdapter
import com.example.guided_project_ss21_a02_kauf_lokal_20_frontend.R
import com.example.guided_project_ss21_a02_kauf_lokal_20_frontend.service.PollingService

/**
 * A fragment representing a list of Items for the newsfeed.
 */
class NewsfeedFragment : Fragment() {

    private var adapter: RecyclerView.Adapter<NewsfeedRecyclerViewAdapter.ViewHolder>? = null

    private var pollingService = PollingService(requireContext())
    private val coupons = pollingService.pollCoupons()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        adapter = NewsfeedRecyclerViewAdapter(coupons)

        return inflater.inflate(R.layout.fragment_newsfeed_list, container, false)
    }
}
package com.siri.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.siri.myapplication.R
import com.siri.myapplication.ui.profile.adapter.JobHiringAdapter
import com.siri.myapplication.ui.profile.data.ServiceOffer

class JobHiringFragment : Fragment(R.layout.fragment_profile_job_hiring) {

    private lateinit var jobHiringRecyclerView: RecyclerView
    private lateinit var jobHiringAdapter: JobHiringAdapter
    private val jobList = mutableListOf<ServiceOffer>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_job_hiring, container, false)

        jobHiringRecyclerView = view.findViewById(R.id.jobHiringRecyclerView)
        jobHiringRecyclerView.layoutManager = LinearLayoutManager(context)

        // Set up the adapter for the RecyclerView
        jobHiringAdapter = JobHiringAdapter(jobList)
        jobHiringRecyclerView.adapter = jobHiringAdapter

        // Fetch job list data (for example, from a ViewModel or a repository)
        loadJobHiringData()

        return view
    }

    private fun loadJobHiringData() {
        // Sample data (you will replace this with actual data from your database or API)
        jobList.clear()

        // Notify the adapter that the data has been updated
        jobHiringAdapter.notifyDataSetChanged()
    }
}

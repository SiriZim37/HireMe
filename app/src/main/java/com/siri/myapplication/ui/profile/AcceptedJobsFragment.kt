package com.siri.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.siri.myapplication.R
import com.siri.myapplication.ui.profile.adapter.AcceptedJobsAdapter
import com.siri.myapplication.ui.profile.data.ServiceOffer

import com.siri.myapplication.util.DatePickerDialogFragment

class AcceptedJobsFragment : Fragment(R.layout.fragment_profile_accepted_jobs) {

    private lateinit var acceptedJobsRecyclerView: RecyclerView
    private lateinit var acceptedJobsAdapter: AcceptedJobsAdapter
    private val acceptedJobList = mutableListOf<ServiceOffer>()
    private lateinit var dateFilterButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_accepted_jobs, container, false)

        acceptedJobsRecyclerView = view.findViewById(R.id.acceptedJobsRecyclerView)
        acceptedJobsRecyclerView.layoutManager = LinearLayoutManager(context)

        // Set up the adapter for the RecyclerView
        acceptedJobsAdapter = AcceptedJobsAdapter(acceptedJobList)
        acceptedJobsRecyclerView.adapter = acceptedJobsAdapter

        // Initialize the filter button and set click listener
        dateFilterButton = view.findViewById(R.id.dateFilterButton)
        dateFilterButton.setOnClickListener {
            // Show date picker dialog for selecting date range
            showDatePickerDialog()
        }

        // Fetch accepted job data (for example, from a ViewModel or a repository)
        loadAcceptedJobData()

        return view
    }

    private fun loadAcceptedJobData() {
        // Sample data (replace with actual data from your database or API)
        acceptedJobList.clear()

        // Notify the adapter that the data has been updated
        acceptedJobsAdapter.notifyDataSetChanged()
    }

    // Show date picker dialog
    private fun showDatePickerDialog() {
        val datePickerFragment = DatePickerDialogFragment()

        // Pass the listener to the fragment
        datePickerFragment.setDateSetListener { selectedDate ->
            // Filter the job list based on the selected date
            filterJobsByDate(selectedDate)
        }

        // Show the date picker dialog using childFragmentManager
        datePickerFragment.show(childFragmentManager, "datePicker")
    }

    // Filter jobs by selected date
    private fun filterJobsByDate(date: String) {
        // You can add actual filtering logic here (for example, comparing the job date with selected date)
       /* val filteredList = acceptedJobList.filter { it.date == date }
        acceptedJobsAdapter.updateJobList(filteredList)*/
    }
}

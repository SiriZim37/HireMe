package com.siri.myapplication.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siri.myapplication.databinding.ItemProfileAcceptedJobBinding
import com.siri.myapplication.ui.profile.data.ServiceOffer


class AcceptedJobsAdapter(private var jobList: List<ServiceOffer>) :
    RecyclerView.Adapter<AcceptedJobsAdapter.AcceptedJobViewHolder>() {

    // ViewHolder to hold the binding for each item in the RecyclerView
    inner class AcceptedJobViewHolder(val binding: ItemProfileAcceptedJobBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcceptedJobViewHolder {
        val binding = ItemProfileAcceptedJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AcceptedJobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AcceptedJobViewHolder, position: Int) {
        val job = jobList[position]
/*        holder.binding.jobTitle.text = job.title
        holder.binding.jobDescription.text = job.description
        holder.binding.jobDate.text = job.date
        holder.binding.jobRating.text = job.averageRating
        holder.binding.jobStatus.text = job.status*/
    }

    override fun getItemCount(): Int = jobList.size

    // Update the job list and notify the adapter of the changes
    fun updateJobList(updatedList: List<ServiceOffer>) {
        jobList = updatedList
        notifyDataSetChanged()
    }
}

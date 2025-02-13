package com.siri.myapplication.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siri.myapplication.databinding.ItemProfileJobHiringBinding
import com.siri.myapplication.ui.profile.data.ServiceOffer


class JobHiringAdapter(private val jobList: List<ServiceOffer>) : RecyclerView.Adapter<JobHiringAdapter.JobHiringViewHolder>() {

    // ViewHolder to hold the binding for each item in the RecyclerView
    inner class JobHiringViewHolder(val binding: ItemProfileJobHiringBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobHiringViewHolder {
        val binding = ItemProfileJobHiringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobHiringViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobHiringViewHolder, position: Int) {
        val job = jobList[position]
/*        holder.binding.jobTitle.text = job.title
        holder.binding.jobDescription.text = job.description
        holder.binding.jobDate.text = job.date
        holder.binding.jobRating.text = job.averageRating*/

        // Handle button clicks to accept, decline or contact
        holder.binding.btnAccept.setOnClickListener {
            // Handle job accept action
        }

        holder.binding.btnDecline.setOnClickListener {
            // Handle job decline action
        }

        holder.binding.btnContact.setOnClickListener {
            // Handle contact action
        }
    }

    override fun getItemCount(): Int = jobList.size
}

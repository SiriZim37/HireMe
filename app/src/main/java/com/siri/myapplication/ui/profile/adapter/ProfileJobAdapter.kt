package com.siri.myapplication.ui.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.siri.myapplication.R

import com.siri.myapplication.ui.profile.data.ServiceOffer

class ProfileJobAdapter(private val jobItemsList: List<ServiceOffer>) : RecyclerView.Adapter<ProfileJobAdapter.JobViewHolder>() {

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jobNameTextView: TextView = itemView.findViewById(R.id.jobName)
        val workingDaysTextView: TextView = itemView.findViewById(R.id.jobDays)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_service_offer, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val jobItem = jobItemsList[position]
        holder.jobNameTextView.text = jobItem.serviceName
        holder.workingDaysTextView.text = jobItem.workingDaysDescription
    }

    override fun getItemCount(): Int = jobItemsList.size
}

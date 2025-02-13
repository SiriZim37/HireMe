package com.siri.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siri.myapplication.data.model.Service
import com.siri.myapplication.databinding.ItemServicesBinding

class ServiceAdapter(private var services: List<Service>) : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {
    private var filteredServices = services

    class ServiceViewHolder(val binding: ItemServicesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = ItemServicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = filteredServices[position]
        holder.binding.serviceName.text = service.name
        holder.binding.serviceDescription.text = service.description
    }

    override fun getItemCount(): Int = filteredServices.size

    fun filter(query: String) {
        filteredServices = if (query.isEmpty()) {
            services
        } else {
            services.filter { it.name.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
}

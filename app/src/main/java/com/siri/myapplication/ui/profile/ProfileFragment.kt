package com.siri.myapplication.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.siri.myapplication.R
import com.siri.myapplication.ui.profile.adapter.ProfileJobAdapter
import com.siri.myapplication.databinding.FragmentProfileBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.siri.myapplication.data.entity.UserEntity
import com.siri.myapplication.data.model.Service
import com.siri.myapplication.ui.MasterViewModel
import com.siri.myapplication.ui.ServiceAdapter
import com.siri.myapplication.ui.profile.data.JobItems
import com.siri.myapplication.ui.profile.data.ServiceOffer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val masterViewModel: MasterViewModel by viewModels()
    private val userViewModel: ProfileViewModel by viewModels() // ViewModel
    private val serviceViewModel: ServiceViewModel by viewModels() // ViewModel
    private lateinit var binding: FragmentProfileBinding
    private val userID = "unique_user_id" // Replace with actual user ID
    private lateinit var registeredJobsRecyclerView: RecyclerView
    private lateinit var addJobButton: Button
    private var jobItemsList = ArrayList<JobItems>()
    private var USER_ID: String? = null
    private var USER_NAME: String? = null

    private lateinit var serviceOffer: List<ServiceOffer>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize binding
        binding = FragmentProfileBinding.bind(view)

        // Fetch User data
        userViewModel.getUser()

        masterViewModel.getAllServices().observe(viewLifecycleOwner) { services ->
            if (services.isNotEmpty()) {
                 jobItemsList = ArrayList<JobItems>().apply {
                    addAll(services.map { serviceEntity ->
                        JobItems(serviceEntity.serviceID, serviceEntity.serviceName)
                    })
                }
            }
        }

        // Observe userLiveData
        userViewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            // Update UI with user data
            if (user != null) {
                user?.let {
                    USER_ID = it.userID
                    USER_NAME =  it.userName
                    binding.edtuserName.setText(it.userName)  // Corrected assignment
                    binding.edtuserEmail.setText(it.userEmail)
                    binding.edtphone.setText(it.phone)
                    binding.edtbirthday.setText(it.birthday)
                    binding.edtcontact.setText(it.contact)
                    binding.edtrole.setText(it.role)
                }
            }

            setServiceOffer()
        }


        // Handle save button click
        binding.saveButton.setOnClickListener {
            saveUserData()
        }



        // Set up add job button
        addJobButton = binding.addJobButton
        addJobButton.setOnClickListener {
            // Show the Add Job Dialog
            showAddJobDialog()
        }
    }

    private fun setServiceOffer() {
        USER_ID?.let { serviceViewModel.getServiceByUserID(it) }

        // Initialize RecyclerView
        registeredJobsRecyclerView = binding.registeredJobsRecyclerView
        val jobAdapter = ProfileJobAdapter(mutableListOf()) // Empty list initially
        registeredJobsRecyclerView.layoutManager = LinearLayoutManager(context)
        registeredJobsRecyclerView.adapter = jobAdapter

        // Observe LiveData from ViewModel
        serviceViewModel.serviceLiveData.observe(viewLifecycleOwner) { serviceOffers ->
            if (!serviceOffers.isNullOrEmpty()) {
                val jobItemsList = serviceOffers.map { service ->
                    JobItems(service.serviceID, service.serviceName)
                }
               // jobAdapter.submitList(jobItemsList) // Update RecyclerView items efficiently
            }
        }
    }


    /* private fun setServiceOffer(): UserEntity? {
         USER_ID?.let { serviceViewModel.getServiceByUserID(it) }

         // Set up RecyclerView with the list of jobs
         registeredJobsRecyclerView = binding.registeredJobsRecyclerView
         val jobAdapter = ProfileJobAdapter(serviceOffer)
         registeredJobsRecyclerView.layoutManager = LinearLayoutManager(context)
         registeredJobsRecyclerView.adapter = jobAdapter

         serviceViewModel.serviceLiveData.observe(viewLifecycleOwner) { serviceOffer ->
             if (serviceOffer.isNotEmpty()) {
                 // Update the jobItemsList with data from the ViewModel
                 jobItemsList.clear()
                 jobItemsList.addAll(serviceOffer.map { service ->
                     JobItems(service.serviceID, service.serviceName)
                 })
                // updateRecyclerView()
             }
         }
     }*/

    private fun showAddJobDialog() {
        val addJobDialog = AddJobDialogFragment()
        val bundle = Bundle().apply {
            putString("userID", USER_ID) // Pass the userID
            putString("userName", USER_NAME) // Pass the userName
            putParcelableArrayList("jobItemsList", jobItemsList)
        }
        addJobDialog.arguments = bundle
        addJobDialog.show(childFragmentManager, "AddJobDialog")
    }

    private fun saveUserData() {
        // Get data from EditText fields
        val userName = binding.edtuserName.text.toString()
        val userEmail = binding.edtuserEmail.text.toString()
        val phone = binding.edtphone.text.toString()
        val birthday = binding.edtbirthday.text.toString()
        val contact = binding.edtcontact.text.toString()
        val role = binding.edtrole.text.toString()

        val userServices = getSelectedServices()

        // Create or update the UserEntity object
        val userEntity = UserEntity(
            userID = "1", // Replace with actual user ID or dynamically set
            userName = userName,
            userEmail = userEmail,
            phone = phone,
            birthday = birthday,
            contact = contact,
            role = role
        )

        // Save the updated UserEntity to the database
        userViewModel.updateUser(userEntity)
    }


    private fun getSelectedServices(): List<String> {
        // Implement this method to return the list of selected services
        // For example, from checkboxes or a spinner, etc.
        return listOf("Service1", "Service2") // Example list
    }

    private fun displayUserServices(userServices: List<String>) {
        // Implement this method to display the services (e.g., checkboxes, list, etc.)
        // Example:
        // - Populate a Spinner or Checkboxes based on the list of services
    }
}

package com.siri.myapplication.ui.profile

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.room.PrimaryKey
import com.siri.myapplication.R
import com.siri.myapplication.data.dao.UserServiceDao
import com.siri.myapplication.data.dao.WorkingDaysDao
import com.siri.myapplication.data.entity.UserEntity
import com.siri.myapplication.data.entity.UserServiceEntity
import com.siri.myapplication.data.entity.WorkingDaysEntity
import com.siri.myapplication.ui.profile.data.JobItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

class AddJobDialogFragment : DialogFragment() {
    private val serviceViewModel: ServiceViewModel by viewModels()
    private lateinit var userID: String
    private lateinit var userName: String
    private lateinit var jobList : List<JobItems>
    private lateinit var jobTypeSpinner: Spinner
    private lateinit var timeFromEditText: EditText
    private lateinit var timeToEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var mondayCheckBox: CheckBox
    private lateinit var tuesdayCheckBox: CheckBox
    private lateinit var wednesdayCheckBox: CheckBox
    private lateinit var thursdayCheckBox: CheckBox
    private lateinit var fridayCheckBox: CheckBox
    private lateinit var saturdayCheckBox: CheckBox
    private lateinit var sundayCheckBox: CheckBox
    private val jobTypes = arrayListOf<String>()
/*    // Inject the UserServiceDao
    @Inject
    lateinit var userServiceDao: UserServiceDao
    @Inject
    lateinit var workingServiceDao: WorkingDaysDao*/


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        userID = arguments?.getString("userID") ?: ""
        userName = arguments?.getString("userName") ?: ""
        jobList = arguments?.getParcelableArrayList<JobItems>("jobItemsList") ?: emptyList()
        // Inflate the dialog layout
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.dialog_profile_cofig_job, null)
        jobTypeSpinner = view.findViewById(R.id.jobTypeSpinner)
        timeFromEditText = view.findViewById(R.id.timeFromEditText)
        timeToEditText = view.findViewById(R.id.timeToEditText)
        submitButton = view.findViewById(R.id.submitButton)
        mondayCheckBox = view.findViewById(R.id.mondayCheckBox)
        tuesdayCheckBox = view.findViewById(R.id.tuesdayCheckBox)
        wednesdayCheckBox = view.findViewById(R.id.wednesdayCheckBox)
        thursdayCheckBox = view.findViewById(R.id.thursdayCheckBox)
        fridayCheckBox = view.findViewById(R.id.fridayCheckBox)
        saturdayCheckBox = view.findViewById(R.id.saturdayCheckBox)
        sundayCheckBox = view.findViewById(R.id.sundayCheckBox)

        // Set up the Spinner with job types
        jobList.forEach { service ->
            jobTypes.add(service.serviceName) // Extract jobName from JobItems
        }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, jobTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        jobTypeSpinner.adapter = adapter

        builder.setView(view)
            .setTitle("Add a New Job")
            .setCancelable(false)

        submitButton.setOnClickListener {
            val jobID: String = jobList
                .filter { it.serviceName == jobTypeSpinner.selectedItem.toString() }
                .map { it.serviceid }
                .firstOrNull() ?: "defaultValue" // Provide a default value if null
            val jobType = jobTypeSpinner.selectedItem.toString()

            val daysOfWeek = mutableListOf<String>()
            if (mondayCheckBox.isChecked) daysOfWeek.add("Monday")
            if (tuesdayCheckBox.isChecked) daysOfWeek.add("Tuesday")
            if (wednesdayCheckBox.isChecked) daysOfWeek.add("Wednesday")
            if (thursdayCheckBox.isChecked) daysOfWeek.add("Thursday")
            if (fridayCheckBox.isChecked) daysOfWeek.add("Friday")
            if (saturdayCheckBox.isChecked) daysOfWeek.add("Saturday")
            if (sundayCheckBox.isChecked) daysOfWeek.add("Sunday")

            val timeFrom = timeFromEditText.text.toString()
            val timeTo = timeToEditText.text.toString()

            saveUserServiceData( jobID, jobType, timeFrom, timeTo, daysOfWeek)

            dismiss()
        }

        return builder.create()
    }

    private fun saveUserServiceData(jobID: String, jobType: String, timeFrom: String, timeTo: String, daysOfWeek: List<String>) {
        // Get values from the UI elements
        val serviceID = jobID
        val serviceName = jobType
        val userID = userID // Or use a unique service ID
        val price = 0.0  // Set a default price or let the user input it
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val location = "Some location"  // Add location logic if needed
        val rating = 4.5f  // You can add a default rating or let the user input it

        // Create UserServiceEntity object
        val userService = UserServiceEntity(
            userID = userID,
            userName = userName,
            serviceName = serviceName,
            serviceID = serviceID,
            price = price,
            workingTimeFrom = timeFrom,
            workingTimeTo = timeTo,
            location = location,
            rating = rating
        )
        val description = StringBuilder() // Initialize StringBuilder
        daysOfWeek.forEach {
            description.append(it).append(",")
        }

        val workingDaysAvailable = WorkingDaysEntity(
            userID = userID,
            serviceID = serviceID,
            d_mo = mondayCheckBox.isChecked ,
            d_tu = tuesdayCheckBox.isChecked ,
            d_wed = wednesdayCheckBox.isChecked ,
            d_thu = thursdayCheckBox.isChecked ,
            d_fri = fridayCheckBox.isChecked ,
            d_sa = saturdayCheckBox.isChecked ,
            d_so = sundayCheckBox.isChecked ,
            des_days = description.toString()
        )
        // Save the data to the database
        saveService(userService)
        saveWorkingDays(workingDaysAvailable)
    }

    private fun saveService(userService: UserServiceEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            serviceViewModel.addService(userService)
        }
    }

    private fun saveWorkingDays(workingDaysAvailable: WorkingDaysEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            serviceViewModel.addServiceWorkingDays(workingDaysAvailable)
        }
    }
}

package com.siri.myapplication.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.siri.myapplication.R
import com.siri.myapplication.data.db.AppDatabase
import com.siri.myapplication.data.entity.UserEntity
import com.siri.myapplication.data.entity.DeviceEntity
import com.siri.myapplication.helper.LocationHelper
import com.siri.myapplication.ui.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RegisterDialogFragment : DialogFragment() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var registerSubmitButton: Button
    private val userViewModel: ProfileViewModel by viewModels() // ViewModel

    private var listener: RegisterDialogListener? = null

    fun setRegisterDialogListener(listener: RegisterDialogListener) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireContext())

        // Inflate the dialog layout
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.dialog_register, null)

        nameEditText = view.findViewById(R.id.registerName)
        emailEditText = view.findViewById(R.id.registerEmail)
        phoneEditText = view.findViewById(R.id.registerPhone)
        registerSubmitButton = view.findViewById(R.id.registerSubmitButton)

        builder.setView(view)
            .setTitle("Register User")
            .setCancelable(true)

        registerSubmitButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString()

            // Get machineId (Device ID)
            val machineId = getMachineId()

            // Get location
            val locationHelper = LocationHelper(requireContext())
            locationHelper.getCurrentLocation { location ->
                val userLocation = location?.let {
                    "${it.latitude}, ${it.longitude}" // or customize to address format
                } ?: "Unknown Location"

                val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
                val userID = "CID-${dateFormat.format(Date())}" // Generate a unique ID
                // Create the UserLoginEntity
                val newDevice = DeviceEntity(
                    id = userID ,
                    machineId = machineId,             // Use the machine ID
                    machineName = "Machine Name",      // You can also get the device name via Build.MODEL
                    location = userLocation,          // User's current location
                    lastLoginTime = System.currentTimeMillis(),
                    currentLoginTime = System.currentTimeMillis(),
                )

                // Create  the UserEntity object
                val userEntity = UserEntity(
                    userID = userID, // Replace with actual user ID or dynamically set
                    userName = name,
                    userEmail = email,
                    phone = phone,
                    birthday = "",
                    contact = "",
                    role = ""
                )

                // Save the new device and new user to the database
                lifecycleScope.launch(Dispatchers.IO) {
                    userViewModel.createDeviceLogin(newDevice)
                    userViewModel.createNewUser(userEntity)
                    dismiss()  // Close the dialog after saving
                }
            }
        }

        return builder.create()
    }

    fun getMachineId(): String {
        return Build.SERIAL // or Build.ID
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener?.onDialogDismissed()
    }
    interface RegisterDialogListener {
        fun onDialogDismissed()
    }

}

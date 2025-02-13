package com.siri.myapplication.util

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    // Define an interface to pass the selected date back to the parent fragment/activity
    private var dateSetListener: ((String) -> Unit)? = null

    fun setDateSetListener(listener: (String) -> Unit) {
        dateSetListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): android.app.Dialog {
        // Use the current date as the default date
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create and return a DatePickerDialog
        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        // Create a string representation of the selected date (e.g., "yyyy-MM-dd")
        val selectedDate = "$year-${month + 1}-$dayOfMonth"

        // Pass the selected date back to the parent fragment/activity via the listener
        dateSetListener?.invoke(selectedDate)
    }
}

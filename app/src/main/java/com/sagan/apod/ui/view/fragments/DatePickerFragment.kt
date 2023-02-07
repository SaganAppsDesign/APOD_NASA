package com.sagan.apod.ui.view.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.sagan.apod.R
import java.util.*

class DatePickerFragment(val listener: (day: Int, month: Int, year: Int) -> Unit): DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onDateSet(view: DatePicker?, day: Int, month: Int, year: Int) {
        listener(day, month, year)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val picker = DatePickerDialog(activity as Context, R.style.DatePickerTheme, this, year, month, day)
        c.set(Calendar.YEAR, 1995)
        c.set(Calendar.MONTH, Calendar.JUNE)
        c.set(Calendar.DAY_OF_MONTH, 16)
        picker.datePicker.minDate = c.timeInMillis
        c.set(year, month, day - 1)
        picker.datePicker.maxDate = c.timeInMillis

        return picker
    }
}
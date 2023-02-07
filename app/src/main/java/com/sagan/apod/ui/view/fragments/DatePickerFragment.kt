package com.sagan.apod.ui.view.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
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
        var today = "1995-06-16"
        val picker = DatePickerDialog(activity as Context, R.style.DatePickerTheme, this, year, month, day)
        c.add(Calendar.YEAR, -27)
        c.add(Calendar.MONTH, -7)
        c.add(Calendar.DAY_OF_MONTH, -3)
        picker.datePicker.minDate = c.timeInMillis
        c.add(Calendar.YEAR, +27)
        c.add(Calendar.MONTH, +7)
        c.add(Calendar.DAY_OF_MONTH, +2)
        picker.datePicker.maxDate = c.timeInMillis
        return picker
    }
}
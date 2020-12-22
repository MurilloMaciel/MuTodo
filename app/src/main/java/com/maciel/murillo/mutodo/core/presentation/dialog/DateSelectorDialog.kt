package com.maciel.murillo.mutodo.core.presentation.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import java.util.*

class DateSelectorDialog(private val callback: DateSelectorCallback) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var calendar: Calendar? = null

    companion object {
        fun show(manager: FragmentManager, callback: DateSelectorCallback) {
            show(manager, null, callback)
        }

        fun show(manager: FragmentManager, calendar: Calendar?, callback: DateSelectorCallback) {
            val dialog = DateSelectorDialog(callback)
            dialog.calendar = calendar
            dialog.show(manager, "DATE_SELECTOR")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initCalendar()
        return DatePickerDialog(requireActivity(), this, getYear(), getMonth(), getDay())
    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        if (view.isShown) {
            callback.onDateSet(year, monthOfYear, dayOfMonth)
        }
    }

    private fun initCalendar() {
        if (calendar == null) {
            this.calendar = Calendar.getInstance()
        }
    }

    private fun getDay(): Int {
        return calendar!!.get(Calendar.DAY_OF_MONTH)
    }

    private fun getMonth(): Int {
        return calendar!!.get(Calendar.MONTH)
    }

    private fun getYear(): Int {
        return calendar!!.get(Calendar.YEAR)
    }
}
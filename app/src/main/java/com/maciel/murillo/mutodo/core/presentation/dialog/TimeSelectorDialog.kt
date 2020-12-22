package com.maciel.murillo.mutodo.core.presentation.dialog

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import java.util.*

class TimeSelectorDialog(private val callback: TimeSelectorCallback) : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    var calendar: Calendar? = null

    companion object {
        fun show(fragmentManager: FragmentManager, callback: TimeSelectorCallback) {
            show(fragmentManager, null, callback)
        }

        fun show(fragmentManager: FragmentManager, currentTime: Calendar?, callback: TimeSelectorCallback) {
            val dialog = TimeSelectorDialog(callback)
            dialog.calendar = currentTime
            dialog.show(fragmentManager, TimeSelectorDialog::class.java.name)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initTime()

        return TimePickerDialog(activity, this, getHour(), getMinute(),
                DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        if (view.isShown) {
            callback.onTimeSet(hourOfDay, minute)
        }
    }

    private fun initTime() {
        if (calendar == null) {
            this.calendar = Calendar.getInstance()
        }
    }

    private fun getMinute(): Int {
        return calendar!!.get(Calendar.MINUTE)
    }

    private fun getHour(): Int {
        return calendar!!.get(Calendar.HOUR_OF_DAY)
    }
}
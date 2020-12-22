package com.maciel.murillo.mutodo.core.presentation.dialog

interface TimeSelectorCallback {

    fun onTimeSet(hourOfDay: Int, minute: Int)
}
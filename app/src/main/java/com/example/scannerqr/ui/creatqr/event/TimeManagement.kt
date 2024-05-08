package com.example.scannerqr.ui.creatqr.event

import android.app.DatePickerDialog
import android.app.TimePickerDialog

interface TimeManagement {

    fun dialogDatePicker(onDateSetListener: DatePickerDialog.OnDateSetListener): TimeManagement
    fun showDatePickerDialog()
    fun dialogTimePicker(onTimeSetListener: TimePickerDialog.OnTimeSetListener): TimeManagement
    fun showTimePickerDialog()
}
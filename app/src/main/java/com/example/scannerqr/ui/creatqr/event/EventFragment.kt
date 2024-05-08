package com.example.scannerqr.ui.creatqr.event

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.DatePicker
import android.widget.Toast
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.databinding.FragmentEventBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class EventFragment : BaseFragmentWithBinding<FragmentEventBinding>() {

    private val calendar = Calendar.getInstance()

    private var _timePickerDialog: TimePickerDialog? = null

    override fun getViewBinding(inflater: LayoutInflater): FragmentEventBinding {
        return FragmentEventBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {
    }

    override fun initAction() {

        binding.toolbar.click {
            onBackPressed()
        }

        showDayAndTime()
    }

    private fun showDayAndTime() {

        binding.txtStartDay.setOnClickListener {
            showDatePicker()
        }

        binding.txtEndDay.setOnClickListener {
            showDatePicker()
        }

        binding.txtStartTime.setOnClickListener {
            showTimePicker()
        }

        binding.txtEndTime.setOnClickListener {
            showTimePicker()
        }

    }

    private fun showDatePicker() {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDate()
            }

        DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateDate() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding.txtStartDay.text = sdf.format(calendar.time)
    }


    private fun showTimePicker() {
        val hourOfDay = 23
        val minute = 55
        val is24HourView = true

        _timePickerDialog = TimePickerDialog(requireContext(), R.style.Theme_Holo_Light_Dialog, { view, hourOfDay, minute ->
                binding.txtStartTime.text = "$hourOfDay:$minute"
            },
            hourOfDay,
            minute,
            is24HourView
        )
        _timePickerDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        _timePickerDialog?.setTitle("Hour : Minute")
        _timePickerDialog?.show()
    }

//    private fun showTimePicker() {
//        val timeSetListener =
//            OnTimeSetListener { view, hourOfDay, minute ->
//                calendar[Calendar.HOUR_OF_DAY] = hourOfDay
//                calendar[Calendar.MINUTE] = minute
//                updateTime()
//            }
//        TimePickerDialog(
//            requireContext(), timeSetListener,
//            calendar[Calendar.HOUR_OF_DAY],
//            calendar[Calendar.MINUTE],
//            true
//        ).show()
//    }
//
//    private fun updateTime() {
//        val myFormat = "HH:mm"
//        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
//        binding.txtStartTime.text = sdf.format(calendar.time)
//    }
}
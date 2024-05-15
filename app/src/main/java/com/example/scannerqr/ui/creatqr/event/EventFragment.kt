package com.example.scannerqr.ui.creatqr.event

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.DatePicker
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.socialmedia.base.utils.click
import com.google.zxing.BarcodeFormat
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

        binding.save.click {
            val input = binding.editTextEventTitle.text.trim().toString()

            if (input.isNotEmpty() && binding.editTextEventTitle.error == null) {
                context?.let { it1 ->
                    context?.let {
                        DialogCreateQr(
                            it,
                            createEventData(
                                binding.title.text.toString(),
                                binding.edtEventLocation.text.toString(),
                                binding.txtStartDay.text.toString() + " " + binding.txtStartTime.text.toString(),
                                binding.txtEndDay.text.toString() + " " + binding.txtEndTime.text.toString(),
                                binding.edtDescribe.text.toString()
                            ),
                            BarcodeFormat.QR_CODE
                        ).show()
                    }
                }
            } else{
                if (input.isEmpty())
                    binding.editTextEventTitle.error = "not value"
            }

        }

        showDayAndTime()
    }

    private fun showDayAndTime() {

        binding.txtStartDay.setOnClickListener {
            showDatePickerStart()
        }

        binding.txtEndDay.setOnClickListener {
            showDatePickerEnd()
        }

        binding.txtStartTime.setOnClickListener {
            showTimePickerStart()
        }

        binding.txtEndTime.setOnClickListener {
            showTimePickerEnd()
        }

    }


    private fun showDatePickerStart() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                startDay()
            }

        DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showDatePickerEnd() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            endDay()
        }

        DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun startDay() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding.txtStartDay.text = sdf.format(calendar.time)
    }

    private fun endDay() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding.txtEndDay.text = sdf.format(calendar.time)
    }


    private fun showTimePickerStart() {
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


    private fun showTimePickerEnd() {
        val hourOfDay = 23
        val minute = 55
        val is24HourView = true

        _timePickerDialog = TimePickerDialog(requireContext(), R.style.Theme_Holo_Light_Dialog, { view, hourOfDay, minute ->
            binding.txtEndTime.text = "$hourOfDay:$minute"
        },
            hourOfDay,
            minute,
            is24HourView
        )
        _timePickerDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        _timePickerDialog?.setTitle("Hour : Minute")
        _timePickerDialog?.show()
    }

    fun createEventData(
        title: String,
        location: String,
        startTime: String,
        endTime: String,
        description: String
    ): String {

        return """
        BEGIN:VEVENT
        SUMMARY:$title
        LOCATION:$location
        DTSTART:${startTime}
        DTEND:${endTime}
        DESCRIPTION:$description
        END:VEVENT
    """.trimIndent()
    }
}
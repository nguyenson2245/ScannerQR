package com.example.scannerqr.ui.creatqr.event

import android.Manifest
import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.widget.DatePicker
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.scannerqr.ui.dialog.DialogPermission
import com.example.socialmedia.base.utils.checkPermission
import com.example.socialmedia.base.utils.click
import com.google.zxing.BarcodeFormat
import com.scan.scannerqr.databinding.FragmentEventBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class EventFragment : BaseFragmentWithBinding<FragmentEventBinding>() {

    private val calendar = Calendar.getInstance()

    private val pattern = "dd/MM/yyyy HH:mm"
    private var _timePickerDialog: TimePickerDialog? = null

    override fun getViewBinding(inflater: LayoutInflater): FragmentEventBinding {
        return FragmentEventBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {
    }

    override fun initAction() {

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.save.click {
            val input = binding.editTextEventTitle.text.trim().toString()

            if (input.isNotEmpty() && binding.editTextEventTitle.error == null) {
                Log.d(
                    TAG,
                    "initAction: " + binding.txtStartDay.text.toString() + " " + binding.txtStartTime.text.toString(),
                )
                context?.let { it1 ->
                    context?.let {

                        DialogCreateQr(
                            this,
                            createEventData(
                                binding.title.text.toString(),
                                binding.edtEventLocation.text.toString(),
                                dateStringToLong(
                                    binding.txtStartDay.text.toString() + " " + binding.txtStartTime.text.toString(),
                                    pattern
                                ),
                                dateStringToLong(
                                    binding.txtEndDay.text.toString() + " " + binding.txtEndTime.text.toString(),
                                    pattern
                                ),
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
        startTime: Long,
        endTime: Long,
        description: String
    ): String {
        val dateFormat = SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.getDefault())

        return """
        BEGIN:VEVENT
        SUMMARY:$title
        LOCATION:$location
        DTSTART:${dateFormat.format(Date(startTime))}
        DTEND:${dateFormat.format(Date(endTime))}
        DESCRIPTION:$description
        END:VEVENT
    """.trimIndent()
    }

    fun dateStringToLong(dateString: String, pattern: String): Long {
        return try {
            val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
            val date = dateFormat.parse(dateString)
            date?.time ?: -1
        } catch (e: Throwable) {
            -1
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 200) {
            if (context?.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == true) {

            } else {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    openActSettingDialog()
            }
        }
    }

    private var showSetting = false
    private fun openActSettingDialog() {
        val dialog = DialogPermission(
            requireContext(),
            getString(com.scan.scannerqr.R.string.anser_grant_permission) + "\n" + getString(com.scan.scannerqr.R.string.goto_setting_and_grant_permission)
        )
        dialog.setPositiveButtonClickListener {
            openSettingApp()
        }

        dialog.setNegativeButtonClickListener {

        }

        dialog.show()
    }

    private fun openSettingApp() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        toast(getString(com.scan.scannerqr.R.string.please_grant_read_external_storage))
        val uri = Uri.fromParts("package", context?.packageName, null)
        intent.data = uri
        startActivity(intent)
        showSetting = true
    }

}
package com.example.scannerqr.ui.creatqr.addQrCode.sms

import android.Manifest
import android.view.LayoutInflater
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.socialmedia.base.utils.click
import com.google.zxing.BarcodeFormat
import com.scan.scannerqr.databinding.FragmentSMSBinding
import java.net.URLEncoder


class SMSFragment : BaseFragmentWithBinding<FragmentSMSBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentSMSBinding {
        return FragmentSMSBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        binding.toolbar.click { onBackPressed() }


        binding.save.click {
            val editPhoneNumber = binding.editPhoneNumber.text.trim().toString()
            if (editPhoneNumber.isNotEmpty() && binding.editPhoneNumber.error == null) {
                context?.let {
                    DialogCreateQr(
                        this@SMSFragment,
                        createSMSData(editPhoneNumber, binding.editMessage.text.toString()),
                        BarcodeFormat.QR_CODE
                    )
                .show()


                }
            } else {
                if (editPhoneNumber.isEmpty())
                    binding.editPhoneNumber.error = "not value"
            }
        }
    }

    private fun createSMSData(phoneNumber: String, message: String): String {
        val smsUri = "smsto:$phoneNumber"
        val encodedMessage = URLEncoder.encode(message, "UTF-8")
        return "$smsUri:$encodedMessage"
    }


}
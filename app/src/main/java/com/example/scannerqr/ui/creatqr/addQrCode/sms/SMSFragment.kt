package com.example.scannerqr.ui.creatqr.addQrCode.sms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.socialmedia.base.utils.click
import com.google.zxing.BarcodeFormat
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentSMSBinding
import java.io.UnsupportedEncodingException
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
                context?.let { it1 ->
                    context?.let {
                        val uri =
                            ("mailto:${binding.editPhoneNumber.text}" + "?subject=" + urlEncode(
                                binding.editPhoneNumber.text.toString()
                            )) + "&body=" + urlEncode(
                                binding.editMessage.text.toString()
                            )
                        context?.let { DialogCreateQr(it, uri, BarcodeFormat.QR_CODE).show() }

                    }
                }
            } else {
                if (editPhoneNumber.isEmpty())
                    binding.editPhoneNumber.error = "not value"
            }
        }
    }

    private fun urlEncode(value: String): String {
        return try {
            URLEncoder.encode(value, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            value
        }
    }

}
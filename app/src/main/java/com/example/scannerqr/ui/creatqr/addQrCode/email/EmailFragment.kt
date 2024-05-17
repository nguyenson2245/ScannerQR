package com.example.scannerqr.ui.creatqr.addQrCode.email

import android.Manifest
import android.view.LayoutInflater
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.base.PermissionFragment
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.socialmedia.base.utils.checkPermission
import com.example.socialmedia.base.utils.click
import com.google.zxing.BarcodeFormat
import com.scan.scannerqr.databinding.FragmentEmailBinding
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class EmailFragment : PermissionFragment<FragmentEmailBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentEmailBinding {
        return FragmentEmailBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        binding.save.click {

            val editEmail = binding.editEmail.text.trim().toString()
            if (editEmail.isNotEmpty() && binding.editEmail.error == null) {
                        val uri = ("mailto:${binding.editEmail.text}" + "?subject=" + urlEncode(binding.editSubject.text.toString())) + "&body=" + urlEncode(binding.editMessage.text.toString())
                         DialogCreateQr(this@EmailFragment, uri, BarcodeFormat.QR_CODE).show()
            } else {
                if (editEmail.isEmpty())
                    binding.editEmail.error = "not value"
            }


        }


        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
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
package com.example.scannerqr.ui.creatqr.addQrCode.text

import android.Manifest
import android.view.LayoutInflater
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.socialmedia.base.utils.checkPermission
import com.example.socialmedia.base.utils.click
import com.google.zxing.BarcodeFormat
import com.scan.scannerqr.databinding.FragmentDocumentBinding


class DocumentFragment : BaseFragmentWithBinding<FragmentDocumentBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentDocumentBinding {
        return FragmentDocumentBinding.inflate(inflater)
    }

    override fun init() {}

    override fun initData() {}

    override fun initAction() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        binding.save.click {

            val input = binding.editText.text.trim().toString()

            if (input.isNotEmpty() && binding.editText.error == null) {
                if (context?.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == true)
                    DialogCreateQr(this, binding.editText.text.toString(), BarcodeFormat.QR_CODE).show()
                else
                    requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 200)
            } else {
                binding.editText.error = "not value"
            }
        }
    }
}
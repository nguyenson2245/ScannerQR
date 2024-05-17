package com.example.scannerqr.ui.creatqr.web

import android.R.attr.bitmap
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.creatqr.shareinotherapp.ShareInOtherAppsFragment
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.socialmedia.base.utils.click
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.scan.scannerqr.databinding.FragmentWebsiteBinding


class WebsiteFragment : BaseFragmentWithBinding<FragmentWebsiteBinding>() {

    companion object {
        fun newInstance() = WebsiteFragment()
    }

    private val viewModel: WebsiteViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater): FragmentWebsiteBinding {
        return FragmentWebsiteBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {

        binding.save.click {

            val input = binding.editText.text.trim().toString()

            if (input.isNotEmpty() && binding.editText.error == null) {
                context?.let { it1 ->
                    if (!input.startsWith("http")) {
                        binding.editText.setText("")
                        binding.editText.error = "Please enter the HTTP link"
                    } else {
                        binding.editText.error = null
                        DialogCreateQr(
                                this,
                                binding.editText.text.toString(),
                                BarcodeFormat.QR_CODE
                            ).show()
                    }
                }
            } else {
                if (input.isEmpty())
                    binding.editText.error = "not value"
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.openShareOther.click {
            openFragment(ShareInOtherAppsFragment::class.java, null, true)
        }

    }
}
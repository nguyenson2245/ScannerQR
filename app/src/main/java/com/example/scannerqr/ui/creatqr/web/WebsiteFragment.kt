package com.example.scannerqr.ui.creatqr.web

import android.R.attr.bitmap
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.socialmedia.base.utils.click
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

        }

    }
}
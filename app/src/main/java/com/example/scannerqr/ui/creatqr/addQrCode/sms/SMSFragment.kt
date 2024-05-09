package com.example.scannerqr.ui.creatqr.addQrCode.sms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentSMSBinding


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
    }


}
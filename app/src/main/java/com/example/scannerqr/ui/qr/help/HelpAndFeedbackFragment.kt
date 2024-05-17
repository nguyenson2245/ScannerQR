package com.example.scannerqr.ui.qr.help

import android.view.LayoutInflater
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.qr.help.scanningDoesNot.ScanningDoesNotFragment
import com.example.scannerqr.ui.qr.help.scanningTips.ScanningTipsFragment
import com.example.scannerqr.ui.qr.help.supportedCodes.OpenHelpSupportedCodesFragment
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.databinding.FragmentHelpAndFeedbackBinding

class HelpAndFeedbackFragment : BaseFragmentWithBinding<FragmentHelpAndFeedbackBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentHelpAndFeedbackBinding {
        return FragmentHelpAndFeedbackBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.supportedCodes.click {
            openFragment(OpenHelpSupportedCodesFragment::class.java,null,true)
        }

        binding.tipsForScanning.click {
            openFragment(ScanningTipsFragment::class.java,null,true)
        }

        binding.scanningdoesnot.click {
            openFragment(ScanningDoesNotFragment::class.java,null,true)
        }
    }



}
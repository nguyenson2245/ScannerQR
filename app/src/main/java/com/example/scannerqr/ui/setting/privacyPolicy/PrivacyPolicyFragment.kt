package com.example.scannerqr.ui.setting.privacyPolicy

import android.view.LayoutInflater
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.scan.scannerqr.databinding.FragmentPrivacyPolicyBinding

class PrivacyPolicyFragment : BaseFragmentWithBinding<FragmentPrivacyPolicyBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentPrivacyPolicyBinding {
        return FragmentPrivacyPolicyBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}
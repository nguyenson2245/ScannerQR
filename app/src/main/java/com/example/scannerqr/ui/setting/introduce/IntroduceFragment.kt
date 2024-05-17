package com.example.scannerqr.ui.setting.introduce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentIntroduceBinding


class IntroduceFragment : BaseFragmentWithBinding<FragmentIntroduceBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroduceBinding {
      return FragmentIntroduceBinding.inflate(inflater)
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
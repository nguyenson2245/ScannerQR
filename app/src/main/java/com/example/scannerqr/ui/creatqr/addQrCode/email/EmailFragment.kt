package com.example.scannerqr.ui.creatqr.addQrCode.email

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentEmailBinding


class EmailFragment : BaseFragmentWithBinding<FragmentEmailBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentEmailBinding {
     return FragmentEmailBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
      binding.toolbar.click {
          onBackPressed()
      }
    }

}
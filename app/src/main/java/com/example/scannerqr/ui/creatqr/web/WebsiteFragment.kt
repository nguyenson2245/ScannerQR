package com.example.scannerqr.ui.creatqr.web

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.scan.scannerqr.R
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

    }
}
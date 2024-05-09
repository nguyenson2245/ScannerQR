package com.example.scannerqr.ui.creatqr.addQrCode.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.creatqr.addQrCode.AddQRCodeViewmodel
import com.example.scannerqr.ui.creatqr.addQrCode.AddQrCodeAdapter
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentAppBinding

class AppFragment : BaseFragmentWithBinding<FragmentAppBinding>() {

    private val viewModel: AppViewmodel by viewModels()
    private lateinit var adapter: AppAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentAppBinding {
        return FragmentAppBinding.inflate(inflater)
    }

    override fun init() {
        adapter = AppAdapter() {
            openFragment(it, null, true)
            toast(it.name)
        }

        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)
    }

    override fun initData() {
        viewModel.initDataApp()
        viewModel.listAddQRCodeLiveData.observe(viewLifecycleOwner) {it->
            adapter.submitList(it)
        }

    }

    override fun initAction() {
        binding.toolbar.click {
            onBackPressed()
        }
    }

}
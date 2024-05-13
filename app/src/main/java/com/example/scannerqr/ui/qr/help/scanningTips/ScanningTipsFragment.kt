package com.example.scannerqr.ui.qr.help.scanningTips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.qr.help.HelpAdapter
import com.example.scannerqr.ui.qr.help.supportedCodes.SupportedCodesViewModel
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentScanningTipsBinding


class ScanningTipsFragment : BaseFragmentWithBinding<FragmentScanningTipsBinding>() {

    private val viewModel: ScanningTipsViewModel by viewModels()
    private lateinit var adapter: HelpAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentScanningTipsBinding {
      return FragmentScanningTipsBinding.inflate(inflater)
    }

    override fun init() {
        adapter = HelpAdapter()
        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)
    }

    override fun initData() {
        viewModel.initDataHelp()
        viewModel.listLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun initAction() {
        binding.toolbar.click {
            onBackPressed()
        }
    }

}
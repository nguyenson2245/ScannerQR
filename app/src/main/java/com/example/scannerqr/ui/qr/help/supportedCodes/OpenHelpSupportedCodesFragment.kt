package com.example.scannerqr.ui.qr.help.supportedCodes

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.qr.help.HelpAdapter
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.databinding.FragmentOpenHelpSupportedCodes2Binding

class OpenHelpSupportedCodesFragment : BaseFragmentWithBinding<FragmentOpenHelpSupportedCodes2Binding>() {

    private val viewModel: SupportedCodesViewModel by viewModels()
    private lateinit var adapter: HelpAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentOpenHelpSupportedCodes2Binding {
      return FragmentOpenHelpSupportedCodes2Binding.inflate(inflater)
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
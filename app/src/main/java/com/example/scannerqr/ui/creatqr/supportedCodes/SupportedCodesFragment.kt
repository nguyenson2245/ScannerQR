package com.example.scannerqr.ui.creatqr.supportedCodes

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.databinding.FragmentSupportedSodesBinding


class SupportedCodesFragment : BaseFragmentWithBinding<FragmentSupportedSodesBinding>() {

    private val viewModel: SupportedCodeViewmodel by viewModels()
    private lateinit var adapter: SupportedCodeAdapter


    override fun getViewBinding(inflater: LayoutInflater): FragmentSupportedSodesBinding {
       return FragmentSupportedSodesBinding.inflate(inflater)
    }

    override fun init() {
        adapter = SupportedCodeAdapter() {
            openFragment(it, null, true)
            toast(it.name)
        }

        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)
    }

    override fun initData() {
        viewModel.initDataCreateQr()
        viewModel.listSpLiveData.observe(viewLifecycleOwner) { it->
            adapter.submitList(it)
        }
    }

    override fun initAction() {
        binding.toolbar.click { onBackPressed() }
    }

}
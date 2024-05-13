package com.example.scannerqr.ui.creatqr.supportedCodes2D

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.creatqr.supportedCodes2D.open.OpenSupportedCodesFragment
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
            val bundle = Bundle()
            bundle.putSerializable("key", it)
            openFragment(OpenSupportedCodesFragment::class.java, bundle, true)
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
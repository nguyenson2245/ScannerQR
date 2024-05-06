package com.example.scannerqr.ui.creatqr

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.scan.scannerqr.databinding.FragmentCreatQrBinding

class CreateQrFragment : BaseFragmentWithBinding<FragmentCreatQrBinding>() {

    companion object {
        fun newInstance() = CreateQrFragment()
    }

    private val viewModel: CreateQrViewModel by viewModels()
    private lateinit var adapter: CreateQrAdapter


    override fun getViewBinding(inflater: LayoutInflater): FragmentCreatQrBinding {
        return FragmentCreatQrBinding.inflate(inflater)
    }

    override fun init() {
        adapter = CreateQrAdapter() {
            openFragment(it, null, true)
            toast(it.name)
        }
        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)
    }

    override fun initData() {
        viewModel.initDataCreateQr()
        viewModel.listCreateQRLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun initAction() {

    }
}
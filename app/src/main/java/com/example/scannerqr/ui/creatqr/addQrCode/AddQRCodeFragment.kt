package com.example.scannerqr.ui.creatqr.addQrCode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.creatqr.CreateQrAdapter
import com.example.scannerqr.ui.creatqr.CreateQrViewModel
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentAddQRCodeBinding


class AddQRCodeFragment : BaseFragmentWithBinding<FragmentAddQRCodeBinding>() {

    private val viewModel: AddQRCodeViewmodel by viewModels()
    private lateinit var adapter: AddQrCodeAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentAddQRCodeBinding {
      return FragmentAddQRCodeBinding.inflate(inflater)
    }

    override fun init() {
        adapter = AddQrCodeAdapter() {
            openFragment(it, null, true)
            toast(it.name)
        }

        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)
    }

    override fun initData() {
        viewModel.initDataCreateQr()
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
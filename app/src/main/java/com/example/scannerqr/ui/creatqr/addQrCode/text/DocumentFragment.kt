package com.example.scannerqr.ui.creatqr.addQrCode.text

import android.view.LayoutInflater
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.databinding.FragmentDocumentBinding


class DocumentFragment : BaseFragmentWithBinding<FragmentDocumentBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentDocumentBinding {
      return FragmentDocumentBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {

        binding.toolbar.click { onBackPressed() }

    }

}
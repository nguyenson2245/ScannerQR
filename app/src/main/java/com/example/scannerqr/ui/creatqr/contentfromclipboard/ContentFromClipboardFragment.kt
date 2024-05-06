package com.example.scannerqr.ui.creatqr.contentfromclipboard

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentContentFromClipboardBinding

class ContentFromClipboardFragment : BaseFragmentWithBinding<FragmentContentFromClipboardBinding>() {

    companion object {
        fun newInstance() = ContentFromClipboardFragment()
    }

    private val viewModel: ContentFromClipboardViewModel by viewModels()


    override fun getViewBinding(inflater: LayoutInflater): FragmentContentFromClipboardBinding {
        return FragmentContentFromClipboardBinding.inflate(inflater)
    }



    override fun init() {


    }

    override fun initData() {
    }

    override fun initAction() {
    }
}
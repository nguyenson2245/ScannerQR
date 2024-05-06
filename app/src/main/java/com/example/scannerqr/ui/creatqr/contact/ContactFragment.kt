package com.example.scannerqr.ui.creatqr.contact

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentContactBinding

class ContactFragment : BaseFragmentWithBinding<FragmentContactBinding>() {

    companion object {
        fun newInstance() = ContactFragment()
    }

    private val viewModel: ContactViewModel by viewModels()


    override fun getViewBinding(inflater: LayoutInflater): FragmentContactBinding {
        return FragmentContactBinding.inflate(inflater)
    }



    override fun init() {

    }

    override fun initData() {
    }

    override fun initAction() {
    }
}
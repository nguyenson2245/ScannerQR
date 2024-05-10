package com.example.scannerqr.ui.qr.detail

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.socialmedia.base.BaseFragment
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentDetailBinding

class DetailFragment : BaseFragmentWithBinding<FragmentDetailBinding>() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater)
    }


    override fun init() {

    }

    override fun initData() {
    }

    override fun initAction() {

    }
}
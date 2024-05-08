package com.example.scannerqr.ui.creatqr.shareinotherapp

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.databinding.FragmentShareInOtherAppsBinding

class ShareInOtherAppsFragment : BaseFragmentWithBinding<FragmentShareInOtherAppsBinding>() {

    companion object {
        fun newInstance() = ShareInOtherAppsFragment()
    }

    private val viewModel: ShareInOtherAppsViewModel by viewModels()


    override fun getViewBinding(inflater: LayoutInflater): FragmentShareInOtherAppsBinding {
        return FragmentShareInOtherAppsBinding.inflate(inflater)
    }


    override fun init() {



    }

    override fun initData() {
        binding.toolbar.click {
            onBackPressed()
        }
    }

    override fun initAction() {
    }
}
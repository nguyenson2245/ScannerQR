package com.example.scannerqr.ui.qr.detail

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.creatqr.addQrCode.app.AppAdapter
import com.example.socialmedia.base.BaseFragment
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentDetailBinding

class DetailFragment : BaseFragmentWithBinding<FragmentDetailBinding>() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var adapter: DetailAdapter



    override fun getViewBinding(inflater: LayoutInflater): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater)
    }


    override fun init() {
        adapter = DetailAdapter() {
            openFragment(it, null, true)
        }

        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)
    }

    override fun initData() {
        viewModel.initDataApp(1)
        viewModel.listDetailsLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    override fun initAction() {

        binding.toolbar.click {
            onBackPressed()
        }

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.delete -> {
                   toast("delete")
                    true
                }
                R.id.outToTxt -> {
                    toast("outToTxt")
                    true
                }
                R.id.more -> {
                    toast("more")
                    true
                }
            }
            true
        }

    }

}
package com.example.scannerqr.ui.setting

import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.model.Settings
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragmentWithBinding<FragmentSettingsBinding>() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private val viewModel: SettingsViewModel by viewModels()
    private lateinit var adapter: SettingsAdapter
    private val listSettings : ArrayList<Settings> = arrayListOf()


    override fun getViewBinding(inflater: LayoutInflater): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater)
    }


    override fun init() {
        adapter = SettingsAdapter()
        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)
    }

    override fun initData() {
    }

    override fun initAction() {

    }
}
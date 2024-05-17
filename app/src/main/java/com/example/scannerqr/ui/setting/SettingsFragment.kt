package com.example.scannerqr.ui.setting

import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.widget.Toast
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.model.Settings
import com.example.scannerqr.ui.qr.help.HelpAndFeedbackFragment
import com.example.scannerqr.ui.utils.Constants
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragmentWithBinding<FragmentSettingsBinding>() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private val viewModel: SettingsViewModel by viewModels()
    private lateinit var adapter: SettingsAdapter


    override fun getViewBinding(inflater: LayoutInflater): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater)
    }

    override fun init() {
        adapter = SettingsAdapter(viewModel,{
            toast("click")
        }){
            openFragment(it,null,true)
        }

        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)
    }

    override fun initData() {
        viewModel.initDataSetting(requireActivity())

        viewModel.listAddSettings.observe(viewLifecycleOwner) {it->
            adapter.submitList(it)
        }
    }

    override fun initAction() {


    }


}
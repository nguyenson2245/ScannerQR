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
    private val listSettings: ArrayList<Settings> = arrayListOf()


    override fun getViewBinding(inflater: LayoutInflater): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater)
    }


    override fun init() {
        adapter = SettingsAdapter()
        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)
    }

    override fun initData() {
        listSettings.add(Settings("General", R.drawable.ic_general))
    }

    override fun initAction() {
        initDataSettings()
    }

    fun initDataSettings() {
        listSettings.add(Settings(title = "General", icon = R.drawable.ic_general))
        listSettings.add(Settings(title = "Display", description = "System default options"))
        listSettings.add(Settings(title = "Help and Feedback"))
        listSettings.add(Settings(title = "Scan control", icon = R.drawable.ic_control))
        listSettings.add(Settings(title = "Open web pages automatically", showButtonSwitch = true))
        listSettings.add(
            Settings(
                title = "Scan continuously",
                description = "Only add scans to history",
                showButtonSwitch = true
            )
        )
        listSettings.add(
            Settings(
                title = "Duplicate barcodes",
                description = "Save duplicate barcodes in history",
                switchEnabled = true,
                showButtonSwitch = true
            )
        )
        listSettings.add(
            Settings(
                title = "Confirm manual scan",
                description = "Avoid accidental scanning",
                showButtonSwitch = true
            )
        )
        listSettings.add(Settings(title = "Sound", showButtonSwitch = true))
        listSettings.add(Settings(title = "Copy to clipboard", showButtonSwitch = true))
        listSettings.add(Settings(title = "Product barcode", icon = R.drawable.ic_settings_barcode))
        listSettings.add(
            Settings(
                title = "Country to search for products",
                description = "System default options"
            )
        )
        listSettings.add(
            Settings(
                title = "Product information",
                description = "Display product information and price if available ",
                showButtonSwitch = true
            )
        )
        listSettings.add(Settings(title ="Introduce", icon = R.drawable.ic_introduce))
        listSettings.add(Settings(title = "Introduce"))
        listSettings.add(Settings(title = "Open source license"))
        listSettings.add(Settings(title = "Privacy policy"))
        listSettings.add(Settings(title = "App version", description = "Version 1.1"))
        adapter.submitList(listSettings)
    }
}
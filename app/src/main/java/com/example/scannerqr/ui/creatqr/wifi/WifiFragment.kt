package com.example.scannerqr.ui.creatqr.wifi

import android.Manifest
import android.content.Intent
import android.os.Build
import android.provider.MediaStore
import android.view.LayoutInflater
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.popup.CustomPopup
import com.example.scannerqr.popup.PopUpOnClickListener
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentWifiBinding

class WifiFragment : BaseFragmentWithBinding<FragmentWifiBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentWifiBinding {
      return FragmentWifiBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {

        binding.toolbar.click {
            onBackPressed()
        }

      binding.imgDropdow.setOnClickListener {
          showPopUp()
      }

    }


    private fun showPopUp()=
        CustomPopup.showPopupMenu(requireContext(), binding.imgDropdow, binding.imgDropdow, getString(R.string.wPA_WPA2),
            R.drawable.ic_control,
            getString(R.string.wap),
            R.drawable.ic_control,
            object : PopUpOnClickListener {
                override fun onClickItemOne() {
                  toast("1111111111111")

                }

                override fun onClickItemTwo() {
                    toast("22222222222222")
                }
            })

}
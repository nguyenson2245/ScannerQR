package com.example.scannerqr.ui.creatqr.wifi

import android.view.LayoutInflater
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.popup.CustomPopup
import com.example.scannerqr.popup.PopUpOnClickListener
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.socialmedia.base.utils.click
import com.google.zxing.BarcodeFormat
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

        binding.save.click {

            val input = binding.edtNetworkName.text.trim().toString()

            if (input.isNotEmpty() && binding.edtNetworkName.error == null) {
                context?.let { it1 ->
                    context?.let {
                        DialogCreateQr(
                            it,
                            binding.edtNetworkName.text.toString(),
                            BarcodeFormat.QR_CODE
                        ).show()
                    }
                }
            } else{
                if (input.isEmpty())
                    binding.edtNetworkName.error = "not value"
            }


        }

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
               binding.txtWPA2.setText(R.string.wPA_WPA2)

                }

                override fun onClickItemTwo() {
                    binding.txtWPA2.setText(R.string.wap)
                }
            })

}
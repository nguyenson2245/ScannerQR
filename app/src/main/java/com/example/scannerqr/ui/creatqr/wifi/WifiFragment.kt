package com.example.scannerqr.ui.creatqr.wifi

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.popup.CustomPopup
import com.example.scannerqr.popup.PopUpOnClickListener
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.scannerqr.ui.dialog.DialogPermission
import com.example.socialmedia.base.utils.checkPermission
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

                        DialogCreateQr(
                            this@WifiFragment,
                            buildWifiString(
                                binding.edtNetworkName.text.toString(),
                                binding.txtWPA2.text.toString(),
                                binding.edtPassword.text.toString()
                            ),
                            BarcodeFormat.QR_CODE
                        ).show()
                }
            } else{
                if (input.isEmpty())
                    binding.edtNetworkName.error = "not value"
            }

        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

      binding.imgDropdow.setOnClickListener {
          showPopUp()
      }

    }

    fun buildWifiString(ssid: String, encryptionType: String, password: String): String {
        return "WIFI:S:$ssid;T:$encryptionType;P:$password;;"
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 200) {
            if (context?.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == true) {

            } else {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    openActSettingDialog()
            }
        }
    }

    private var showSetting = false
    private fun openActSettingDialog() {
        val dialog = DialogPermission(
            requireContext(),
            getString(R.string.anser_grant_permission) + "\n" + getString(R.string.goto_setting_and_grant_permission)
        )
        dialog.setPositiveButtonClickListener {
            openSettingApp()
        }

        dialog.setNegativeButtonClickListener {

        }

        dialog.show()
    }

    private fun openSettingApp() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        toast(getString(R.string.please_grant_read_external_storage))
        val uri = Uri.fromParts("package", context?.packageName, null)
        intent.data = uri
        startActivity(intent)
        showSetting = true
    }

}
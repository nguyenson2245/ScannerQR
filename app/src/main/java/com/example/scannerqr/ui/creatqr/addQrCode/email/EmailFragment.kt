package com.example.scannerqr.ui.creatqr.addQrCode.email

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.base.PermissionFragment
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.scannerqr.ui.dialog.DialogPermission
import com.example.socialmedia.base.utils.checkPermission
import com.example.socialmedia.base.utils.click
import com.google.zxing.BarcodeFormat
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentEmailBinding
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class EmailFragment : PermissionFragment<FragmentEmailBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentEmailBinding {
        return FragmentEmailBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        binding.save.click {

            val editEmail = binding.editEmail.text.trim().toString()
            if (editEmail.isNotEmpty() && binding.editEmail.error == null) {
                        val uri = ("mailto:${binding.editEmail.text}" + "?subject=" + urlEncode(binding.editSubject.text.toString())) + "&body=" + urlEncode(binding.editMessage.text.toString())

                if (context?.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == true || Build.VERSION.SDK_INT > Build.VERSION_CODES.S_V2)
                DialogCreateQr(this@EmailFragment, uri, BarcodeFormat.QR_CODE).show()
                else requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 200)
            } else {
                if (editEmail.isEmpty())
                    binding.editEmail.error = "not value"
            }


        }


        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun urlEncode(value: String): String {
        return try {
            URLEncoder.encode(value, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            value
        }
    }

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
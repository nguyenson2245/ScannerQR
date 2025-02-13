package com.example.scannerqr.ui.creatqr.supportedCodes2D.open

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.InputFilter
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import androidx.core.widget.doOnTextChanged
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.scannerqr.ui.dialog.DialogPermission
import com.example.socialmedia.base.utils.checkPermission
import com.example.socialmedia.base.utils.click
import com.example.socialmedia.base.utils.gone
import com.example.socialmedia.base.utils.visible
import com.google.zxing.BarcodeFormat
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentOpenSupportedCodesBinding

class OpenSupportedCodesFragment : BaseFragmentWithBinding<FragmentOpenSupportedCodesBinding>() {

    var title: BarcodeFormat = BarcodeFormat.QR_CODE

    override fun getViewBinding(inflater: LayoutInflater): FragmentOpenSupportedCodesBinding {
        return FragmentOpenSupportedCodesBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {
        title = arguments?.getSerializable("key") as BarcodeFormat
        if (title != null) {
            binding.txtToolBar.text = title.toString()
        }

        binding.edtTitle.doOnTextChanged { text, start, before, count ->
            val input = binding.edtTitle.text.trim().toString()
            when (title) {

                BarcodeFormat.QR_CODE -> {
                    binding.edtTitle.hint = "text"
                }

                BarcodeFormat.DATA_MATRIX -> {
                    binding.edtTitle.hint = "text without special characters"
                    val specialCharactersRegex = Regex("[^a-zA-Z0-9 ]")

                    if (specialCharactersRegex.containsMatchIn(input)) {
                        binding.edtTitle.error = "err !\n" + " Text without special characters !"

                    } else {
                        binding.edtTitle.error = null
                    }
                }

                BarcodeFormat.PDF_417 -> {
                    binding.edtTitle.hint = "text"

                    val textOnlyRegex = Regex("^[a-zA-Z ]+\$")

                    if (!textOnlyRegex.matches(input)) {
                        binding.edtTitle.error = "Text input only!"
                    } else {
                        binding.edtTitle.error = null
                    }
                }

                BarcodeFormat.AZTEC -> {
                    binding.edtTitle.hint = "text without special characters"

                    val specialCharactersRegex = Regex("^[a-zA-Z0-9 ]+\$")

                    if (!specialCharactersRegex.matches(input)) {
                        binding.edtTitle.error = "Text without special characters!"
                    } else {
                        binding.edtTitle.error = null
                    }
                }

                BarcodeFormat.EAN_13 -> {
                    binding.edtTitle.hint = "12 digits + 1 checksum digit"
                    binding.edtTitle.inputType = InputType.TYPE_CLASS_NUMBER

                    val ean13Regex = Regex("^[0-9]{12}\$")
                    val input = binding.edtTitle.text.toString()

                    if (input.length != 13 || !input.matches(ean13Regex)) {
                        binding.edtTitle.error =
                            "The input must contain exactly 12 digits followed by 1 digit for checksum!"
                    } else {
                        binding.edtTitle.error = null
                    }
                }

                BarcodeFormat.EAN_8 -> {
                    binding.edtTitle.hint = "8 digit"
                    binding.edtTitle.inputType = InputType.TYPE_CLASS_NUMBER

                    val digitRegex = Regex("^[0-9]{8}\$")
                    if (input.length != 8 || !input.matches(digitRegex)) {
                        binding.edtTitle.error = "The input must contain exactly 8 digits!"
                    } else {
                        binding.edtTitle.error = null
                    }
                }

                BarcodeFormat.UPC_E -> {
                    binding.edtTitle.hint = "7 digits + 1 total digit"

                    val digitRegex = Regex("^[0-9]{7}[0-9]\$")

                    if (digitRegex.matches(input)) {
                        binding.edtTitle.error =
                            "Input must contain exactly 7 digits followed by 1 digit for total!"
                    } else {
                        binding.edtTitle.error = null
                    }
                }

                BarcodeFormat.UPC_A -> {
                    binding.edtTitle.hint = "11 digits + 1 total digit"

                    val digitRegex = Regex("^[0-9]{11}[0-9]\$")

                    if (digitRegex.matches(input)) {
                        binding.edtTitle.error =
                            "Input must contain exactly 11 digits followed by 1 total digit!"
                    } else {
                        binding.edtTitle.error = null
                    }
                }

                BarcodeFormat.CODE_128 -> {
                    binding.edtTitle.hint = "Text without special characters"

                    val specialCharactersRegex = Regex("^[a-zA-Z0-9 ]+\$")

                    if (!specialCharactersRegex.matches(input)) {
                        binding.edtTitle.error = "Text without special characters!"
                    } else {
                        binding.edtTitle.error = null
                    }
                }

                BarcodeFormat.CODE_93 -> {
                    binding.edtTitle.hint = "Capitalized text has no special characters"

                    val capitalizedTextRegex = Regex("^[A-Z]+$")
                    if (!capitalizedTextRegex.matches(input)) {
                        binding.edtTitle.error = "Text without special characters!"
                    } else {
                        binding.edtTitle.error = null
                    }
                }

                BarcodeFormat.CODE_39 -> {
                    binding.edtTitle.hint = "Capitalized text has no special characters"

                    val capitalizedTextRegex = Regex("^[A-Z]+$")
                    if (!capitalizedTextRegex.matches(input) && input.length < 2) {
                        binding.edtTitle.error = "Text without special characters!"
                    } else {
                        binding.edtTitle.error = null
                    }
                }

                BarcodeFormat.CODABAR -> {
                    binding.edtTitle.hint = "Number"
                    binding.edtTitle.inputType = InputType.TYPE_CLASS_NUMBER

                    binding.edtTitle.inputType = InputType.TYPE_CLASS_NUMBER

                    val digitRegex = Regex("^[0-9]{8}\$")
                    if (input.length != 8 || !input.matches(digitRegex)) {
                        binding.edtTitle.error = "The input must contain exactly 8 digits!"
                    } else {
                        binding.edtTitle.error = null
                    }
                }

                BarcodeFormat.ITF -> {
                    val maxLength = 10

                    binding.edtTitle.hint = "Limit the number of digits"
                    binding.edtTitle.filters = arrayOf(InputFilter.LengthFilter(maxLength))

                    if (input.length > maxLength) {
                        binding.edtTitle.error = "Maximum $maxLength digits allowed!"
                    } else {
                        binding.edtTitle.error = null
                    }
                }

                BarcodeFormat.MAXICODE -> Log.d("BarcodeFormat", "MAXI CODE: ")
                BarcodeFormat.RSS_14 -> Log.d("BarcodeFormat", "RSS_14: ")
                BarcodeFormat.RSS_EXPANDED -> Log.d("BarcodeFormat", "RSS_EXPANDED: ")
                BarcodeFormat.UPC_EAN_EXTENSION -> Log.d("BarcodeFormat", "UPC_EAN_EXTENSION: ")

                null -> Log.d("BarcodeFormat", "err: ")
            }
        }
    }

    override fun initAction() {

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.save.click {
            val input = binding.edtTitle.text.trim().toString()
            if (input.isNotEmpty() && binding.edtTitle.error == null) {
                if(context?.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == true  || Build.VERSION.SDK_INT> Build.VERSION_CODES.S_V2)
                    DialogCreateQr(
                        this,
                        binding.edtTitle.text.toString(),
                        title
                    ).show()
                else requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),200)
            } else
                if (input.isEmpty())
                    binding.edtTitle.error = "not value"
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
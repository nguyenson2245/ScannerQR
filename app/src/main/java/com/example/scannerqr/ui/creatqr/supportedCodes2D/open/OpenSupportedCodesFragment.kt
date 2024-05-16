package com.example.scannerqr.ui.creatqr.supportedCodes2D.open

import android.text.InputFilter
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import androidx.core.widget.doOnTextChanged
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.socialmedia.base.utils.click
import com.google.zxing.BarcodeFormat
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
            val input = text?.trim().toString()
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
                    if (!capitalizedTextRegex.matches(input)) {
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

        binding.toolbar.click {
            onBackPressed()
        }

        binding.save.click {
            val input = binding.edtTitle.text.trim().toString()
            if (input.isNotEmpty() && binding.edtTitle.error == null) {
                    DialogCreateQr(
                        this,
                        binding.edtTitle.text.toString(),
                        title
                    ).show()
            } else
                if (input.isEmpty())
                    binding.edtTitle.error = "not value"
        }

    }

}
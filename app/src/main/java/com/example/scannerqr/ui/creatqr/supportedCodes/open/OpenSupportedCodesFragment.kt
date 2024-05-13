package com.example.scannerqr.ui.creatqr.supportedCodes.open

import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.socialmedia.base.utils.click
import com.google.zxing.BarcodeFormat
import com.scan.scannerqr.databinding.FragmentOpenSupportedCodesBinding

class OpenSupportedCodesFragment : BaseFragmentWithBinding<FragmentOpenSupportedCodesBinding>() {

    var title:BarcodeFormat? = null
    override fun getViewBinding(inflater: LayoutInflater): FragmentOpenSupportedCodesBinding {
        return FragmentOpenSupportedCodesBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {
        title = arguments?.getSerializable("key") as BarcodeFormat?
        if (title != null) {
            binding.txtToolBar.text = title.toString()
        }

        when(title){
            BarcodeFormat.QR_CODE ->{
                binding.edtTitle.hint = "text"
            }
            BarcodeFormat.DATA_MATRIX -> {
                binding.edtTitle.hint = "text without special characters"
            }
            BarcodeFormat.PDF_417 ->{
                binding.edtTitle.hint = "text"

            }
            BarcodeFormat.AZTEC ->{
                binding.edtTitle.hint = "text without special characters"

            }
            BarcodeFormat.EAN_13 -> {
                binding.edtTitle.hint = "12 digits + 1 checksum digit"
                binding.edtTitle.inputType = InputType.TYPE_CLASS_NUMBER

            }
            BarcodeFormat.EAN_8 -> {
                binding.edtTitle.hint = "8 digit"
                binding.edtTitle.inputType = InputType.TYPE_CLASS_NUMBER
            }
            BarcodeFormat.UPC_E -> {
                binding.edtTitle.hint = "7 digits + 1 total digit"
            }
            BarcodeFormat.UPC_A -> {
                binding.edtTitle.hint = "11 digits + 1 total digit"
            }
            BarcodeFormat.CODE_128 -> {
                binding.edtTitle.hint = "Text without special characters"
            }
            BarcodeFormat.CODE_93 -> {
                binding.edtTitle.hint = "Capitalized text has no special characters"
            }
            BarcodeFormat.CODE_39 -> {
                binding.edtTitle.hint = "Capitalized text has no special characters"
            }
            BarcodeFormat.CODABAR -> {
                binding.edtTitle.hint = "Number"
                binding.edtTitle.inputType = InputType.TYPE_CLASS_NUMBER
            }
            BarcodeFormat.ITF ->{
                binding.edtTitle.hint = "Limit the number of digits"
            }

            BarcodeFormat.MAXICODE ->  Log.d("BarcodeFormat", "MAXICODE: ")
            BarcodeFormat.RSS_14 ->  Log.d("BarcodeFormat", "RSS_14: ")
            BarcodeFormat.RSS_EXPANDED ->  Log.d("BarcodeFormat", "RSS_EXPANDED: ")
            BarcodeFormat.UPC_EAN_EXTENSION ->  Log.d("BarcodeFormat", "UPC_EAN_EXTENSION: ")

            null -> Log.d("BarcodeFormat", "err: ")
            BarcodeFormat.AZTEC -> TODO()
        }
    }

    override fun initAction() {
        binding.toolbar.click {
            onBackPressed()
        }

        binding.save.click {
            toast("save")
        }

    }

}
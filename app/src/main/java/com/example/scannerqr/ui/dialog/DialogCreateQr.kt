package com.example.scannerqr.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.example.socialmedia.base.utils.click
import com.google.zxing.WriterException
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.DialogCreatQrBinding


class DialogCreateQr(context: Context, val inputValue: String) : Dialog(context) {
    private lateinit var binding: DialogCreatQrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(context.getDrawable(R.drawable.bg_dialog))
        binding = DialogCreatQrBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        init()
    }

    private fun init() {
        val qrgEncoder = QRGEncoder(inputValue, null, QRGContents.Type.TEXT,TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 300f, context.getResources().getDisplayMetrics()).toInt() )

        try {
            val bitmap = qrgEncoder.getBitmap()
            binding.imageQr.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            Log.v(TAG, e.toString())
        }
        binding.btnSave.click {
            dismiss()
        }
        binding.btnCancle.click {
            dismiss()
        }

    }
}

package com.example.scannerqr.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import com.example.socialmedia.base.utils.click
import com.example.socialmedia.base.utils.dpToPx
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
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

        binding.imageQr.setImageBitmap(createImage(inputValue, "QR Code"))

        binding.btnSave.click {
            dismiss()
        }
        binding.btnCancle.click {
            dismiss()
        }

    }

    fun createImage(message: String?, type: String?): Bitmap {
        var bitMatrix: BitMatrix? = null
        bitMatrix = when (type) {
            "QR Code" -> MultiFormatWriter().encode(
                message, BarcodeFormat.QR_CODE,
                300.dpToPx(context.resources), 300.dpToPx(context.resources)
            )


            "Data Matrix" -> MultiFormatWriter().encode(
                message, BarcodeFormat.DATA_MATRIX,
                300.dpToPx(context.resources), 300.dpToPx(context.resources)
            )

            "PDF 417" -> MultiFormatWriter().encode(
                message,
                BarcodeFormat.PDF_417,
                300.dpToPx(context.resources),
                120.dpToPx(context.resources)
            )

            "Barcode-39" -> MultiFormatWriter().encode(
                message,
                BarcodeFormat.CODE_39,
                300.dpToPx(context.resources),
                120.dpToPx(context.resources)
            )

            "Barcode-93" -> MultiFormatWriter().encode(
                message,
                BarcodeFormat.CODE_93,
                300.dpToPx(context.resources),
                120.dpToPx(context.resources)
            )

            "AZTEC" -> MultiFormatWriter().encode(
                message, BarcodeFormat.AZTEC,
                300.dpToPx(context.resources), 300.dpToPx(context.resources)
            )

            else -> MultiFormatWriter().encode(
                message, BarcodeFormat.QR_CODE,
                300.dpToPx(context.resources), 300.dpToPx(context.resources)
            )
        }
        val width = bitMatrix.width
        val height = bitMatrix.height
        val pixels = IntArray(width * height)
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (bitMatrix[j, i]) {
                    pixels[i * width + j] = -0x1000000
                } else {
                    pixels[i * width + j] = -0x1
                }
            }
        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

}

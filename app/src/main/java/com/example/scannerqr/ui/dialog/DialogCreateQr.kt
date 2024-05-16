package com.example.scannerqr.ui.dialog

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.socialmedia.base.utils.checkPermission
import com.example.socialmedia.base.utils.click
import com.example.socialmedia.base.utils.dpToPx
import com.example.socialmedia.base.utils.gone
import com.example.socialmedia.base.utils.visible
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.DialogCreatQrBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class DialogCreateQr(val fragment: BaseFragmentWithBinding<*>, val inputValue: String, val type: BarcodeFormat) : Dialog(fragment.requireContext()) {
    private lateinit var binding: DialogCreatQrBinding
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(context.getDrawable(R.drawable.bg_dialog))
        binding = DialogCreatQrBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        init()
    }

    private fun init() {

        binding.imageQr.setImageBitmap(createImage(inputValue, type))

        binding.btnSave.click {
            checkPermission(context)
            val bitmap = createImage(inputValue, type)
            saveBitmapToStorage(context, bitmap, "${System.currentTimeMillis()}qrcode.png")
            dismiss()
        }

        binding.btnCancle.click {
            dismiss()
        }
    }

    fun createImage(message: String?, type: BarcodeFormat): Bitmap {
        var bitMatrix: BitMatrix? = null
        bitMatrix = when (type) {
            BarcodeFormat.QR_CODE -> MultiFormatWriter().encode(
                message, BarcodeFormat.QR_CODE,
                300.dpToPx(context.resources), 300.dpToPx(context.resources)
            )

            BarcodeFormat.DATA_MATRIX -> MultiFormatWriter().encode(
                message, BarcodeFormat.DATA_MATRIX,
                300.dpToPx(context.resources), 300.dpToPx(context.resources)
            )

            BarcodeFormat.PDF_417 -> MultiFormatWriter().encode(
                message,
                BarcodeFormat.PDF_417,
                300.dpToPx(context.resources),
                300.dpToPx(context.resources)
            )

            BarcodeFormat.AZTEC -> MultiFormatWriter().encode(
                message, BarcodeFormat.AZTEC,
                300.dpToPx(context.resources), 120.dpToPx(context.resources)
            )

            BarcodeFormat.EAN_13 -> MultiFormatWriter().encode(
                message,
                BarcodeFormat.EAN_13,
                300.dpToPx(context.resources),
                120.dpToPx(context.resources)
            )

            BarcodeFormat.EAN_8 -> MultiFormatWriter().encode(
                message,
                BarcodeFormat.EAN_8,
                300.dpToPx(context.resources),
                120.dpToPx(context.resources)
            )

            BarcodeFormat.UPC_E -> MultiFormatWriter().encode(
                message,
                BarcodeFormat.UPC_E,
                300.dpToPx(context.resources),
                120.dpToPx(context.resources)
            )

            BarcodeFormat.UPC_A -> MultiFormatWriter().encode(
                message,
                BarcodeFormat.UPC_A,
                300.dpToPx(context.resources),
                120.dpToPx(context.resources)
            )

            BarcodeFormat.CODE_128 -> MultiFormatWriter().encode(
                message,
                BarcodeFormat.CODE_128,
                300.dpToPx(context.resources),
                120.dpToPx(context.resources)
            )

            BarcodeFormat.CODE_93 -> MultiFormatWriter().encode(
                message,
                BarcodeFormat.CODE_93,
                300.dpToPx(context.resources),
                120.dpToPx(context.resources)
            )

            BarcodeFormat.CODE_39 -> MultiFormatWriter().encode(
                message,
                BarcodeFormat.CODE_39,
                300.dpToPx(context.resources),
                120.dpToPx(context.resources)
            )

            BarcodeFormat.CODABAR -> MultiFormatWriter().encode(
                message,
                BarcodeFormat.CODE_39,
                300.dpToPx(context.resources),
                120.dpToPx(context.resources)
            )

            BarcodeFormat.ITF -> MultiFormatWriter().encode(
                message,
                BarcodeFormat.CODE_39,
                300.dpToPx(context.resources),
                120.dpToPx(context.resources)
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


    fun saveBitmapToStorage(context: Context, bitmap: Bitmap, fileName: String) {
        val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "MyImages")
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val file = File(directory, fileName)

        var outputStream: OutputStream? = null
        try {
            outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            outputStream?.close()
        }
    }

    fun checkPermission(){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(fragment.requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        } else {
            Toast.makeText(, "", Toast.LENGTH_SHORT).show()
        }
    }


}

package com.example.scannerqr.ui.creatqr.contentfromclipboard

import android.Manifest
import android.content.ClipboardManager
import android.content.ContentValues
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.dialog.DialogPermission
import com.example.socialmedia.base.utils.checkPermission
import com.example.socialmedia.base.utils.click
import com.example.socialmedia.base.utils.doSendBroadcast
import com.example.socialmedia.base.utils.gone
import com.example.socialmedia.base.utils.visible
import com.google.zxing.WriterException
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentContentFromClipboardBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class ContentFromClipboardFragment :
    BaseFragmentWithBinding<FragmentContentFromClipboardBinding>() {
    var bitmap: Bitmap? = null
    companion object {
        fun newInstance() = ContentFromClipboardFragment()
    }

    private val viewModel: ContentFromClipboardViewModel by viewModels()


    override fun getViewBinding(inflater: LayoutInflater): FragmentContentFromClipboardBinding {
        return FragmentContentFromClipboardBinding.inflate(inflater)
    }


    override fun init() {

        val clipboard: ClipboardManager? =
            context?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
        if (clipboard != null && clipboard.text != null) {
            val qrgEncoder = QRGEncoder(
                clipboard.text.toString(), null, QRGContents.Type.TEXT, TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 300f, context?.getResources()?.getDisplayMetrics()
                ).toInt()
            )
            try {
               bitmap = qrgEncoder.getBitmap()
                binding.qrImage.setImageBitmap(bitmap)
            } catch (e: WriterException) {
                Log.v(ContentValues.TAG, e.toString())
            }
        } else {

        }
    }

    override fun initData() {
    }

    override fun initAction() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.save.click {
            if (bitmap != null) {
                if (context?.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == true || Build.VERSION.SDK_INT> Build.VERSION_CODES.S_V2)
                saveBitmapToStorage(requireActivity(), bitmap!!, "${System.currentTimeMillis()}qrcode.png")
                else requestPermissions(arrayOf( Manifest.permission.WRITE_EXTERNAL_STORAGE), 200)
            }else toast("Error")
        }
    }
    private fun saveBitmapToStorage(context: Context, bitmap: Bitmap, fileName: String) {
        val directory = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), context.getString(
                R.string.app_name))
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val file = File(directory, fileName)

        var outputStream: OutputStream? = null
        try {
            outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            context.doSendBroadcast(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            outputStream?.close()
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
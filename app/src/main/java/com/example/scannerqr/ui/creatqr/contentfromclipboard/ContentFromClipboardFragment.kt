package com.example.scannerqr.ui.creatqr.contentfromclipboard

import android.content.ClipboardManager
import android.content.ContentValues
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.socialmedia.base.utils.click
import com.google.zxing.WriterException
import com.scan.scannerqr.databinding.FragmentContentFromClipboardBinding


class ContentFromClipboardFragment :
    BaseFragmentWithBinding<FragmentContentFromClipboardBinding>() {

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
                val bitmap = qrgEncoder.getBitmap()
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
    }
}
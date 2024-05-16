package com.example.scanqr.ui.qr

import android.Manifest
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.view.LayoutInflater
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.custom.ScannerView
import com.example.scannerqr.local.Preferences
import com.example.scannerqr.ui.MainViewModel
import com.example.scannerqr.ui.qr.detail.DetailFragment
import com.example.scannerqr.ui.qr.help.HelpAndFeedbackFragment
import com.example.socialmedia.base.utils.checkPermission
import com.example.socialmedia.base.utils.click
import com.example.socialmedia.base.utils.gone
import com.example.socialmedia.base.utils.visible
import com.example.socialmedia.ui.home.post.gallery.GalleryImageFragment
import com.google.zxing.BinaryBitmap
import com.google.zxing.ChecksumException
import com.google.zxing.FormatException
import com.google.zxing.LuminanceSource
import com.google.zxing.MultiFormatReader
import com.google.zxing.NotFoundException
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.Reader
import com.google.zxing.Result
import com.google.zxing.common.HybridBinarizer
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentQrcodeBinding

class QrcodeFragment : BaseFragmentWithBinding<FragmentQrcodeBinding>(), ScannerView.ResultHandler {

    companion object {
        fun newInstance() = QrcodeFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private var flashLightStatus: Boolean = false
    private  var preferences : Preferences ? = null
    override fun getViewBinding(inflater: LayoutInflater): FragmentQrcodeBinding {
        return FragmentQrcodeBinding.inflate(inflater)
    }

    override fun init() {
        preferences = context?.let { Preferences.getInstance(it) }
        checkPermissionX()
    }

    override fun initData() {
        viewModel.bitmap.observe(viewLifecycleOwner) {
            if (it != null) {
                val result = readQRImage(it)
                if (result != null) {
                    val bundle = Bundle()
                    bundle.putString("value", result)
                    bundle.putBoolean("isBack", true)
                    viewModel.isPlayCamera.postValue(false)
                    openFragment(DetailFragment::class.java, bundle, true)
                } else {
                    toast("Can't read qr")
                }
                viewModel.bitmap.postValue(null)
            }
        }
        viewModel.isPlayCamera.observe(viewLifecycleOwner) {
            if (it) {
                binding.scannerView.resumeCameraPreview(this)

            }
        }
    }

    override fun onStart() {
        super.onStart()
        checkPermissionX()
    }

    private fun checkPermissionX() {
        if (context?.checkPermission(Manifest.permission.CAMERA) == true) {
            binding.scannerView.setResultHandler(this)
            binding.scannerView.startCamera();
            binding.viewScanner.visible()
            binding.noScanCheckPer.gone()
        } else
            when {
                context?.checkPermission(Manifest.permission.CAMERA) == false -> {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), 200)
                }

                shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), 200)
                }

                else -> {
                    toast("showDialog")
                }
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 200) {
            if (context?.checkPermission(Manifest.permission.CAMERA) == true) {
                binding.scannerView.setResultHandler(this)
                binding.scannerView.startCamera();
                binding.viewScanner.visible()
                binding.noScanCheckPer.gone()
            } else {
                binding.viewScanner.gone()
                binding.noScanCheckPer.visible()
            }
        }
    }

    private fun readQRImage(bMap: Bitmap): String? {
        var contents: String? = null
        try {
            val intArray = IntArray(bMap.getWidth() * bMap.getHeight())
            bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(), bMap.getHeight())
            val source: LuminanceSource =
                RGBLuminanceSource(bMap.getWidth(), bMap.getHeight(), intArray)
            val bitmap = BinaryBitmap(HybridBinarizer(source))
            val reader: Reader = MultiFormatReader()

            val result: Result = reader.decode(bitmap)
            contents = result.text
        } catch (e: NotFoundException) {
            e.printStackTrace()
        } catch (e: ChecksumException) {
            e.printStackTrace()
        } catch (e: FormatException) {
            e.printStackTrace()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return contents
    }

    override fun initAction() {
        binding.openFlash.click {
            binding.scannerView.flash = !flashLightStatus
            flashLightStatus = !flashLightStatus
        }
        binding.btnOpenLibrary.click {
            openFragment(GalleryImageFragment::class.java, null, true)
        }

        binding.btnHelp.click {
            openFragment(HelpAndFeedbackFragment::class.java, null, true)
        }
        binding.btnZoomIn.setOnClickListener {
            binding.seekBar.progress += 10
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.scannerView.zoom(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        binding.btnZoomOut.setOnClickListener {
            binding.seekBar.progress -= 10
        }

        binding.scanWithCamera.click {
            checkPermissionX()

        }
        binding.scanImages.click {
            openFragment(GalleryImageFragment::class.java, null, true)
        }

        binding.btnStore.click {
            toast("Store")
        }
    }

    override fun handleResult(rawResult: Result?) {
        if (preferences?.getBoolean(com.example.scannerqr.ui.utils.Constants.SOUND) == true){
            val mediaPlayer: MediaPlayer =
                MediaPlayer.create(context, R.raw.musicmp3)
            mediaPlayer.start();
        }
        if (preferences?.getBoolean(com.example.scannerqr.ui.utils.Constants.))
        val contents = rawResult.toString()
        val bundle = Bundle()
        bundle.putString("value", contents)
        bundle.putBoolean("isBack", true)
        bundle.putSerializable("type_value", checkTypeValue(contents))
        viewModel.isPlayCamera.postValue(false)
        openFragment(DetailFragment::class.java, bundle, true)
    }

    private fun checkTypeValue(contents: String): TypeValue {
        return when {
            contents.startsWith("WIFI:") -> TypeValue.TYPE_WIFI
            contents.startsWith("BEGIN:VCARD") -> TypeValue.TYPE_VCARD
            contents.startsWith("http://") || contents.startsWith("https://") -> TypeValue.TYPE_WEB
            contents.startsWith("BEGIN:VEVENT") -> TypeValue.TYPE_EVENT
            contents.startsWith("mailto:") -> TypeValue.TYPE_MAIL
            contents.startsWith("smsto:") -> TypeValue.TYPE_SMS
            else -> TypeValue.TYPE_CONTENT
        }
    }




    override fun onPause() {
        binding.scannerView.flash = false
        flashLightStatus = false
        super.onPause()
    }

}

enum class TypeValue() {
    TYPE_WIFI, TYPE_CONTENT, TYPE_VCARD, TYPE_EVENT, TYPE_WEB, TYPE_MAIL, TYPE_SMS
}
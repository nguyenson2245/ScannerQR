package com.example.scanqr.ui.qr

import android.Manifest
import android.graphics.Bitmap
import android.os.Handler
import android.view.LayoutInflater
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.custom.ScannerView
import com.example.scannerqr.ui.MainViewModel
import com.example.scannerqr.ui.qr.help.HelpAndFeedbackFragment
import com.example.socialmedia.base.utils.checkPermission
import com.example.socialmedia.base.utils.click
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
import com.permissionx.guolindev.PermissionX
import com.scan.scannerqr.databinding.FragmentQrcodeBinding


class QrcodeFragment : BaseFragmentWithBinding<FragmentQrcodeBinding>(), ScannerView.ResultHandler {

    companion object {
        fun newInstance() = QrcodeFragment()
    }


    private val viewModel: MainViewModel by activityViewModels()
    private var flashLightStatus : Boolean = false
    override fun getViewBinding(inflater: LayoutInflater): FragmentQrcodeBinding {
       return FragmentQrcodeBinding.inflate(inflater)
    }

    override fun init() {


    }

    override fun onResume() {
        super.onResume()
        if (context?.checkPermission(Manifest.permission.CAMERA) == true) {
            binding.scannerView.setResultHandler(this)
            binding.scannerView.startCamera();
            binding.scannerView.flash = false
        } else{
            PermissionX.init(this)
                .permissions( Manifest.permission.CAMERA)
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        binding.scannerView.setResultHandler(this)
                        binding.scannerView.startCamera();
                    } else {
                        Toast.makeText(requireContext(),  "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    override fun initData() {
        viewModel.bitmap.observe(viewLifecycleOwner) {
            if (it != null){
                Toast.makeText(requireContext(),readQRImage(it), Toast.LENGTH_SHORT).show()
                viewModel.bitmap.postValue(null)
            }

        }
    }

   private fun readQRImage(bMap: Bitmap): String? {
        var contents: String? = null
        val intArray = IntArray(bMap.getWidth() * bMap.getHeight())
        //copy pixel data from the Bitmap into the 'intArray' array
        bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(), bMap.getHeight())
        val source: LuminanceSource =
            RGBLuminanceSource(bMap.getWidth(), bMap.getHeight(), intArray)
        val bitmap = BinaryBitmap(HybridBinarizer(source))
        val reader: Reader = MultiFormatReader() // use this otherwise ChecksumException
        try {
            val result: Result = reader.decode(bitmap)
            contents = result.text
        } catch (e: NotFoundException) {
            e.printStackTrace()
        } catch (e: ChecksumException) {
            e.printStackTrace()
        } catch (e: FormatException) {
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
            binding.seekBar.progress = binding.seekBar.progress + 10
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
            binding.seekBar.progress = binding.seekBar.progress - 10
        }

    }

    override fun handleResult(rawResult: Result?) {
        Toast.makeText(
            activity, ("Contents = " + rawResult?.getText()).toString() +
                    ", Form at = " + rawResult?.getBarcodeFormat().toString(), Toast.LENGTH_SHORT
        ).show()

        val handler = Handler()
        handler.postDelayed(
            Runnable { binding.scannerView.resumeCameraPreview(this) },
            1000
        )
    }

    override fun onPause() {
        binding.scannerView.flash = false
        flashLightStatus= false
        super.onPause()
    }

}
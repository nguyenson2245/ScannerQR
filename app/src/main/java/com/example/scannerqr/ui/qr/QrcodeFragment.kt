package com.example.scanqr.ui.qr

import android.Manifest
import android.content.Context.VIBRATOR_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.custom.ScannerView
import com.example.scannerqr.local.Preferences
import com.example.scannerqr.local.preference.PrefHelper
import com.example.scannerqr.model.History
import com.example.scannerqr.ui.MainViewModel
import com.example.scannerqr.ui.dialog.DialogPermission
import com.example.scannerqr.ui.inapp.PurchaseActivity
import com.example.scannerqr.ui.qr.detail.DetailFragment
import com.example.scannerqr.ui.qr.help.HelpAndFeedbackFragment
import com.example.scannerqr.ui.utils.Constants
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
import java.text.SimpleDateFormat
import java.util.Date

class QrcodeFragment : BaseFragmentWithBinding<FragmentQrcodeBinding>(), ScannerView.ResultHandler {

    companion object {
        fun newInstance() = QrcodeFragment()
    }

    private lateinit var pref: PrefHelper
    private var currentCoin = 0

    private val viewModel: MainViewModel by activityViewModels()
    private val qrcodeViewModel: QrcodeViewModel by viewModels()
    private var flashLightStatus: Boolean = false
    private  var preferences : Preferences ? = null
    override fun getViewBinding(inflater: LayoutInflater): FragmentQrcodeBinding {
        return FragmentQrcodeBinding.inflate(inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = PrefHelper.getInstance(requireContext())!!
        currentCoin = pref.getValueCoin()
    }

    override fun init() {
        binding.tvCurrentCoin.text = pref.getValueCoin().toString()

        preferences = context?.let { Preferences.getInstance(it) }
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
            checkPermissionCamera()

        }
        binding.scanImages.click {
            openFragment(GalleryImageFragment::class.java, null, true)
        }

        binding.btnStore.click {
            startActivity(Intent(context, PurchaseActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        checkPermissionCamera()
    }

    private fun checkPermissionCamera() {
        if (context?.checkPermission(Manifest.permission.CAMERA) == true) {
            binding.scannerView.setResultHandler(this)
            binding.scannerView.startCamera();
            binding.viewScanner.visible()
            binding.noScanCheckPer.gone()
        } else
            requestPermissions(arrayOf(Manifest.permission.CAMERA), 200)


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
                if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA))
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




    private fun shakeItBaby() {
        if (Build.VERSION.SDK_INT >= 26) {
            (context?.getSystemService(VIBRATOR_SERVICE) as Vibrator?)?.vibrate(
                VibrationEffect.createOneShot(
                    150,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            (context?.getSystemService(VIBRATOR_SERVICE) as Vibrator?)?.vibrate(150)
        }
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

    override fun handleResult(rawResult: Result?) {
        val contents = rawResult.toString()
        if (preferences?.getBoolean(Constants.SOUND) == true) {
            val mediaPlayer: MediaPlayer =
                MediaPlayer.create(context, R.raw.musicmp3)
            mediaPlayer.start();
        }
        if (preferences?.getBoolean(Constants.VIBRATE) == true) {
            shakeItBaby()
        }
        if (preferences?.getBoolean(Constants.SCAN) == true) {
            qrcodeViewModel.addHistory(
                requireContext(), History(
                    0, contents, R.drawable.seecode, SimpleDateFormat("dd/MM/yyyy HH:mm").format(
                        Date(System.currentTimeMillis())
                    )
                )
            )
            Handler(Looper.getMainLooper()).postDelayed({
                binding.scannerView.resumeCameraPreview(this)
            }, 50)
        } else {
            val bundle = Bundle()
            bundle.putString("value", contents)
            bundle.putBoolean("isBack", true)
            bundle.putSerializable("type_value", checkTypeValue(contents))
            viewModel.isPlayCamera.postValue(false)
            openFragment(DetailFragment::class.java, bundle, true)
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
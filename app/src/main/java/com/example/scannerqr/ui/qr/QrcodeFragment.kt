package com.example.scanqr.ui.qr

import android.Manifest
import android.os.Handler
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.scannerqr.custom.ScannerView
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.socialmedia.base.utils.checkPermission
import com.google.zxing.Result
import com.permissionx.guolindev.PermissionX
import com.scan.scannerqr.databinding.FragmentQrcodeBinding


class QrcodeFragment : BaseFragmentWithBinding<FragmentQrcodeBinding>(), ScannerView.ResultHandler {

    companion object {
        fun newInstance() = QrcodeFragment()
    }



    private val viewModel: QrcodeViewModel by viewModels()
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

    }

    override fun initAction() {

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



}
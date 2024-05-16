package com.example.scannerqr.ui.creatqr

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.utils.PermissionUtils
import com.example.socialmedia.base.utils.checkPermission
import com.example.socialmedia.base.utils.gone
import com.example.socialmedia.base.utils.visible
import com.permissionx.guolindev.PermissionX
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentCreatQrBinding

class CreateQrFragment : BaseFragmentWithBinding<FragmentCreatQrBinding>() {

    companion object {
        fun newInstance() = CreateQrFragment()
    }

    private val viewModel: CreateQrViewModel by viewModels()
    private lateinit var adapter: CreateQrAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentCreatQrBinding {
        return FragmentCreatQrBinding.inflate(inflater)
    }

    override fun init() {

        adapter = CreateQrAdapter() {
            openFragment(it, null, true)
        }

        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)
    }

    override fun initData() {
        viewModel.initDataCreateQr()
        viewModel.listCreateQRLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun initAction() {

    }

//    private fun checkPermissionUT() {
//
//        if (!PermissionUtils.checkStoragePermission(requireActivity())) {
//            PermissionX.init(this)
//                .permissions(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Manifest.permission.WRITE_EXTERNAL_STORAGE else Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .onExplainRequestReason { scope, deniedList ->
//                    scope.showRequestReasonDialog(
//                        deniedList,
//                        "Core fundamental are based on these permissions",
//                        "OK",
//                        "Cancel"
//                    )
//                }
//                .request { allGranted, grantedList, deniedList ->
//
//                }
//        } else {
//            openSettingApp()
//        }
//    }
    private var showSetting = false

    private fun openSettingApp() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        toast(getString(R.string.please_grant_read_external_storage))
        val uri = Uri.fromParts("package", requireActivity().packageName, null)
        intent.data = uri
        startActivity(intent)
        showSetting = true
    }
}
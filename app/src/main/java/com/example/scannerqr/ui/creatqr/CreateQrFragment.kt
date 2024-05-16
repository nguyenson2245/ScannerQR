package com.example.scannerqr.ui.creatqr

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import android.provider.Settings
import android.util.Log
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

        if (context?.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == true) {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            startActivityForResult(intent, 1)
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 200)

        }

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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 200)
            if (context?.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == true) {
                val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                startActivityForResult(intent, 1)
            } else {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) || !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) )
                    Log.d("TAG", "onRequestPermissionsResult: +Grant permissions ")
            }
    }


}
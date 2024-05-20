package com.example.socialmedia.ui.home.post.gallery

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.dialog.DialogPermission
import com.example.scannerqr.ui.qr.cropimage.CropImageFragment
import com.example.socialmedia.base.utils.checkPermission
import com.example.socialmedia.base.utils.click
import com.example.socialmedia.base.utils.gone
import com.example.socialmedia.base.utils.visible
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentGalleryImageBinding

class GalleryImageFragment : BaseFragmentWithBinding<FragmentGalleryImageBinding>() {

    companion object {
        fun newInstance() = GalleryImageFragment()
    }

    private val viewModel: GalleryImageViewModel by viewModels()
    private lateinit var galleryAdapter: GalleryAdapter
    override fun getViewBinding(inflater: LayoutInflater): FragmentGalleryImageBinding {
        return FragmentGalleryImageBinding.inflate(inflater)
    }

    override fun init() {
        galleryAdapter = GalleryAdapter() {
            val bundle = Bundle()
            bundle.putString("uri", it.uri)
            openFragment(CropImageFragment::class.java, bundle, true)
        }
        binding.rcvView.adapter = galleryAdapter
        binding.rcvView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        if (context?.checkPermission(if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S_V2) Manifest.permission.READ_MEDIA_IMAGES else Manifest.permission.READ_EXTERNAL_STORAGE) == true)
            context?.let { viewModel.getAllImages(it) }
        else requestPermissions(
            arrayOf(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2) Manifest.permission.READ_MEDIA_IMAGES else Manifest.permission.READ_EXTERNAL_STORAGE),
            200
        )

    }

    override fun initAction() {

        binding.ivBack.click {
            onBackPressed()
        }

        binding.btnNext.click {
            onBackPressed()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 200) {
            if (context?.checkPermission(Manifest.permission.CAMERA) == true) {
                context?.let { viewModel.getAllImages(it) }
            } else {
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

    @SuppressLint("NotifyDataSetChanged")
    override fun initData() {
        viewModel.imageAllGrallery.observe(viewLifecycleOwner) {
            val listImage = it?.toMutableList()?.reversed()
            galleryAdapter.submitList(listImage)
            if (listImage?.isNotEmpty() == true) {
                binding.rcvView.visible()
                binding.layoutNoData.gone()
            }else {
                binding.layoutNoData.visible()
                binding.rcvView.gone()
            }
        }
    }
}
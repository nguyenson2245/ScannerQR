package com.example.socialmedia.ui.home.post.gallery

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.scannerqr.base.PermissionFragment
import com.example.scannerqr.ui.qr.cropimage.CropImageFragment
import com.example.socialmedia.base.utils.click
import com.example.socialmedia.base.utils.gone
import com.example.socialmedia.base.utils.visible
import com.scan.scannerqr.databinding.FragmentGalleryImageBinding

class GalleryImageFragment : PermissionFragment<FragmentGalleryImageBinding>() {

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
        openGallery {
            context?.let { viewModel.getAllImages(it) }
        }
    }

    override fun initAction() {

        binding.ivBack.click {
            onBackPressed()
        }

        binding.btnNext.click {
            onBackPressed()
        }
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
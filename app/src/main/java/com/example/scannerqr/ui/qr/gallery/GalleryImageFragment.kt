package com.example.socialmedia.ui.home.post.gallery

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.scannerqr.base.PermissionFragment
import com.example.socialmedia.base.utils.click
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

        }
        binding.rcvView.adapter = galleryAdapter
        binding.rcvView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        openGallery {
            context?.let { viewModel.getAllImages(it) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initData() {
        viewModel.imageAllGrallery.observe(viewLifecycleOwner) {
            val listImage = it?.toMutableList()?.reversed()
            Log.d(TAG, "initData: "+ it?.size)
            galleryAdapter.submitList(listImage)
        }

    }

    override fun initAction() {
        binding.materialToolbar.click {
            onBackPressed()
        }
        binding.btnNext.click {
            onBackPressed()
        }
    }
}
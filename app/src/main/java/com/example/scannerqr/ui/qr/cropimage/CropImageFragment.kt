package com.example.scannerqr.ui.qr.cropimage

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.activityViewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.MainActivity
import com.example.scannerqr.ui.MainViewModel
import com.example.socialmedia.base.utils.click
import com.example.socialmedia.ui.home.post.gallery.GalleryImageFragment
import com.scan.scannerqr.databinding.FragmentCropImageBinding

class CropImageFragment : BaseFragmentWithBinding<FragmentCropImageBinding>() {

    companion object {
        fun newInstance() = CropImageFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private var uri: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uri = arguments?.getString("uri", "") ?: ""
    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentCropImageBinding {
        return FragmentCropImageBinding.inflate(inflater)
    }

    override fun init() {
        if (!uri.isNullOrEmpty()) {
            binding.cropImageView.setImageBitmap(BitmapFactory.decodeFile(uri))
            binding.cropImageView.setCenterMoveEnabled(true)
        }
        binding.save.click {
            val bitmap = binding.cropImageView.getCroppedImage()
            (activity as MainActivity).supportFragmentManager.popBackStack(
                GalleryImageFragment::class.java.simpleName,
                POP_BACK_STACK_INCLUSIVE
            )
            viewModel.bitmap.postValue(bitmap)
        }


    }

    override fun initData() {

    }

    override fun initAction() {

    }
}
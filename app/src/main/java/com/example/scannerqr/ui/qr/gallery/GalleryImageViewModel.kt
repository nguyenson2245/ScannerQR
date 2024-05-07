package com.example.socialmedia.ui.home.post.gallery

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scannerqr.model.ImageModel
import com.example.scannerqr.repository.Repository
import com.example.socialmedia.base.BaseViewModel
import com.example.socialmedia.common.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryImageViewModel() : BaseViewModel() {
    private val _imageAllGrallery : MutableLiveData<ArrayList<ImageModel>?> = MutableLiveData()
    val imageAllGrallery: LiveData<ArrayList<ImageModel>?> get() =_imageAllGrallery
    fun getAllImages(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllImages(context){
                when(it){
                    is State.Success -> {
                        isLoading.postValue(false)
                       _imageAllGrallery.postValue(it.data)
                    }
                    else -> {

                    }
                }
            }
        }
    }
}
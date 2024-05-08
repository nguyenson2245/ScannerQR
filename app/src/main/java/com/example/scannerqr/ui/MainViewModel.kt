package com.example.scannerqr.ui

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.example.socialmedia.base.BaseViewModel

class MainViewModel : BaseViewModel() {
    var bitmap : MutableLiveData<Bitmap?> = MutableLiveData()
}
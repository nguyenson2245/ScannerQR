package com.example.socialmedia.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scannerqr.repository.Repository

abstract class BaseViewModel() : ViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
}
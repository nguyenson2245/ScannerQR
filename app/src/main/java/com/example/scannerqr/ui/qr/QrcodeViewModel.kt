package com.example.scanqr.ui.qr

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scannerqr.model.History
import com.example.scannerqr.repository.Repository
import com.example.socialmedia.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QrcodeViewModel : BaseViewModel() {
    private val repository = Repository()
    fun addHistory(context: Context, history: History) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHistory(context, history)
        }
    }

}
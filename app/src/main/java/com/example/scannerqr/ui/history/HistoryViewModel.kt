package com.example.scannerqr.ui.history

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.scannerqr.local.AppDatabase
import com.example.scannerqr.repository.Repository
import com.example.socialmedia.base.BaseViewModel

class HistoryViewModel : BaseViewModel() {

    private val repository = Repository()
    fun getLiveDateHistory(context: Context) = repository.getLiveDataHistory(context)

}
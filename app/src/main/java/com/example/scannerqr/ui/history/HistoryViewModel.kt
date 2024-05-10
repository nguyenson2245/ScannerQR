package com.example.scannerqr.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scannerqr.model.CreateQR
import com.example.scannerqr.model.History
import com.example.scanqr.ui.qr.QrcodeFragment
import com.example.socialmedia.base.BaseViewModel
import com.scan.scannerqr.R

class HistoryViewModel : BaseViewModel() {


    private val listHistory: ArrayList<History> = arrayListOf()
    val listHistoryLiveData: MutableLiveData<ArrayList<History>> = MutableLiveData()

    fun initDataCreateQr() {
        listHistory.add(
            History(
                0,
                "Hello",
                R.drawable.ic_edit,
                "13/5/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(
                0,
                "Bart Simpson",
                R.drawable.ic_edit,
                "23/6/4048",
                "10:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "03/2/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "13/1/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "3/5/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "13/7/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "13/5/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "13/5/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "13/5/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )

    }


}
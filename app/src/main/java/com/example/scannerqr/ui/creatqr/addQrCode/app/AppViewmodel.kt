package com.example.scannerqr.ui.creatqr.addQrCode.app

import androidx.lifecycle.MutableLiveData
import com.example.scannerqr.model.App
import com.example.socialmedia.base.BaseViewModel
import com.scan.scannerqr.R

class AppViewmodel : BaseViewModel() {

    private val  listApp: ArrayList<App> = arrayListOf()
    val listAddQRCodeLiveData: MutableLiveData<ArrayList<App>> = MutableLiveData()

    fun initDataApp() {
        listApp.add(App("Document", R.drawable.pen2 ))
        listApp.add(App("Email", R.drawable.email2 ))
        listApp.add(App("SMS", R.drawable.sms ))
        listApp.add(App("App", R.drawable.ic_add_qr))
        listApp.add(App("Document", R.drawable.pen2 ))
        listApp.add(App("Email", R.drawable.email2 ))
        listApp.add(App("SMS", R.drawable.sms ))
        listApp.add(App("App", R.drawable.ic_add_qr))
        listApp.add(App("Document", R.drawable.pen2 ))
        listApp.add(App("Email", R.drawable.email2 ))
        listApp.add(App("SMS", R.drawable.sms ))
        listApp.add(App("App", R.drawable.ic_add_qr))

        listAddQRCodeLiveData.postValue(listApp)
    }
}
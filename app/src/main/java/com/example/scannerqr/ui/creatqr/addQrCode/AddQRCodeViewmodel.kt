package com.example.scannerqr.ui.creatqr.addQrCode

import androidx.lifecycle.MutableLiveData
import com.example.scannerqr.model.AddQRCode
import com.example.scannerqr.ui.creatqr.contact.ContactFragment
import com.example.scannerqr.ui.creatqr.event.EventFragment
import com.example.scannerqr.ui.creatqr.web.WebsiteFragment
import com.example.scannerqr.ui.creatqr.wifi.WifiFragment
import com.example.socialmedia.base.BaseViewModel
import com.scan.scannerqr.R

class AddQRCodeViewmodel : BaseViewModel() {

    private val  listAddQRCode: ArrayList<AddQRCode> = arrayListOf()
    val listAddQRCodeLiveData: MutableLiveData<ArrayList<AddQRCode>> = MutableLiveData()

    fun initDataCreateQr() {
        listAddQRCode.add(AddQRCode("Document", R.drawable.pen2, WebsiteFragment::class.java))
        listAddQRCode.add(AddQRCode("Email", R.drawable.email2, ContactFragment::class.java))
        listAddQRCode.add(AddQRCode("SMS", R.drawable.sms, WifiFragment::class.java))
        listAddQRCode.add(AddQRCode("Event", R.drawable.ic_add_qr, EventFragment::class.java))

        listAddQRCodeLiveData.postValue( listAddQRCode)
    }
}
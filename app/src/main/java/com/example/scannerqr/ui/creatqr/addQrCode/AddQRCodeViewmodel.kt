package com.example.scannerqr.ui.creatqr.addQrCode

import androidx.lifecycle.MutableLiveData
import com.example.scannerqr.model.AddQRCode
import com.example.scannerqr.ui.creatqr.addQrCode.text.DocumentFragment
import com.example.scannerqr.ui.creatqr.addQrCode.email.EmailFragment
import com.example.scannerqr.ui.creatqr.addQrCode.sms.SMSFragment
import com.example.socialmedia.base.BaseViewModel
import com.scan.scannerqr.R

class AddQRCodeViewmodel : BaseViewModel() {

    private val  listAddQRCode: ArrayList<AddQRCode> = arrayListOf()
    val listAddQRCodeLiveData: MutableLiveData<ArrayList<AddQRCode>> = MutableLiveData()

    fun initDataCreateQr() {
        listAddQRCode.add(AddQRCode("Document", R.drawable.pen2, DocumentFragment::class.java))
        listAddQRCode.add(AddQRCode("Email", R.drawable.email2, EmailFragment::class.java))
        listAddQRCode.add(AddQRCode("SMS", R.drawable.sms, SMSFragment::class.java))
//        listAddQRCode.add(AddQRCode("App", R.drawable.ic_add_qr, AppFragment::class.java))

        listAddQRCodeLiveData.postValue(listAddQRCode)
    }
}
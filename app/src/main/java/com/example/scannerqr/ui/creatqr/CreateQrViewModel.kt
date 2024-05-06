package com.example.scannerqr.ui.creatqr

import androidx.lifecycle.MutableLiveData
import com.example.scannerqr.model.CreateQR
import com.example.scannerqr.ui.creatqr.contact.ContactFragment
import com.example.scannerqr.ui.creatqr.contentfromclipboard.ContentFromClipboardFragment
import com.example.scannerqr.ui.creatqr.shareinotherapp.ShareInOtherAppsFragment
import com.example.scannerqr.ui.creatqr.web.WebsiteFragment
import com.example.scannerqr.ui.history.HistoryFragment
import com.example.scanqr.ui.qr.QrcodeFragment
import com.example.socialmedia.base.BaseViewModel
import com.scan.scannerqr.R

class CreateQrViewModel : BaseViewModel() {
    private val listCreateQR: ArrayList<CreateQR> = arrayListOf()
    val listCreateQRLiveData: MutableLiveData<ArrayList<CreateQR>> = MutableLiveData()

    fun initDataCreateQr() {
        listCreateQR.add(
            CreateQR(
                "Use “Share” in other apps",
                R.drawable.ic_share,
                ShareInOtherAppsFragment::class.java
            )
        )
        listCreateQR.add(
            CreateQR(
                "Content from clipboard", R.drawable.ic_coppy_content,
                ContentFromClipboardFragment::class.java
            )
        )
        listCreateQR.add(CreateQR("Website", R.drawable.ic_web, WebsiteFragment::class.java))
        listCreateQR.add(CreateQR("Contact", R.drawable.ic_contact, ContactFragment::class.java))
        listCreateQR.add(CreateQR("Wifi", R.drawable.ic_wifi, QrcodeFragment::class.java))
        listCreateQR.add(CreateQR("Event", R.drawable.ic_event, QrcodeFragment::class.java))
        listCreateQR.add(CreateQR("Add QR code", R.drawable.ic_add_qr, QrcodeFragment::class.java))
        listCreateQR.add(
            CreateQR(
                "Barcodes and other 2D codes",
                R.drawable.ic_barcode,
                QrcodeFragment::class.java
            )
        )
        listCreateQRLiveData.postValue(listCreateQR)
    }
}
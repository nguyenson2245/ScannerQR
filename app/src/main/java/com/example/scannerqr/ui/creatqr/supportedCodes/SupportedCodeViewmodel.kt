package com.example.scannerqr.ui.creatqr.supportedCodes

import androidx.lifecycle.MutableLiveData
import com.example.scannerqr.model.Supported
import com.example.socialmedia.base.BaseViewModel
import com.scan.scannerqr.R

class SupportedCodeViewmodel : BaseViewModel() {

    private val  listAddQRCode: ArrayList<Supported> = arrayListOf()
    val listSpLiveData: MutableLiveData<ArrayList<Supported>> = MutableLiveData()

    fun initDataCreateQr() {
        listAddQRCode.add(Supported("QR code", R.drawable.ic_add_qr,"Document"))
        listAddQRCode.add(Supported("Data Matrix", R.drawable.ic_add_qr,"Text without special characters"))
        listAddQRCode.add(Supported("PDF 417", R.drawable.ic_add_qr,"Document"))

        listAddQRCode.add(Supported("Aztec", R.drawable.barcode1,"Text without special characters"))
        listAddQRCode.add(Supported("EAN 13", R.drawable.barcode1,"12 digits + 1 total digit"))
        listAddQRCode.add(Supported("EAN 8  ", R.drawable.barcode1,"8 digits"))
        listAddQRCode.add(Supported("UPC E", R.drawable.barcode1,"7 digits + 1 total digit"))
        listAddQRCode.add(Supported("UPC A", R.drawable.barcode1,"11 digits + 1 total digit"))
        listAddQRCode.add(Supported("Code 128", R.drawable.barcode1,"Text without special characters"))
        listAddQRCode.add(Supported("Code 93", R.drawable.barcode1,"Capitalized text has no special characters"))
        listAddQRCode.add(Supported("Code 39", R.drawable.barcode1,"Capitalized text has no special characters"))
        listAddQRCode.add(Supported("Codabar", R.drawable.barcode1,"Number"))
        listAddQRCode.add(Supported("ITF", R.drawable.barcode1,"Limit the number of digits"))

        listSpLiveData.postValue(listAddQRCode)
    }
}
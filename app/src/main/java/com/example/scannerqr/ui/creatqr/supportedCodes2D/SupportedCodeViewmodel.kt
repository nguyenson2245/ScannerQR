package com.example.scannerqr.ui.creatqr.supportedCodes2D

import androidx.lifecycle.MutableLiveData
import com.example.scannerqr.model.Supported
import com.example.scannerqr.ui.creatqr.supportedCodes2D.open.OpenSupportedCodesFragment
import com.example.socialmedia.base.BaseViewModel
import com.google.zxing.BarcodeFormat
import com.scan.scannerqr.R

class SupportedCodeViewmodel : BaseViewModel() {

    private val  listAddQRCode: ArrayList<Supported> = arrayListOf()
    val listSpLiveData: MutableLiveData<ArrayList<Supported>> = MutableLiveData()

    fun initDataCreateQr() {
        listAddQRCode.add(Supported("QR code", R.drawable.ic_add_qr,"Document",key = BarcodeFormat.QR_CODE, fragmentOpen = OpenSupportedCodesFragment::class.java))
        listAddQRCode.add(Supported("Data Matrix", R.drawable.ic_add_qr,"Text without special characters", key = BarcodeFormat.DATA_MATRIX,fragmentOpen = OpenSupportedCodesFragment::class.java))
        listAddQRCode.add(Supported("PDF 417", R.drawable.ic_add_qr,"Document",key = BarcodeFormat.PDF_417,fragmentOpen = OpenSupportedCodesFragment::class.java))

        listAddQRCode.add(Supported("Aztec", R.drawable.barcode1,"Text without special characters",key = BarcodeFormat.AZTEC,fragmentOpen = OpenSupportedCodesFragment::class.java))
        listAddQRCode.add(Supported("EAN 13", R.drawable.barcode1,"12 digits + 1 total digit",key = BarcodeFormat.EAN_13,fragmentOpen = OpenSupportedCodesFragment::class.java))
        listAddQRCode.add(Supported("EAN 8  ", R.drawable.barcode1,"8 digits",key = BarcodeFormat.EAN_8))
        listAddQRCode.add(Supported("UPC E", R.drawable.barcode1,"7 digits + 1 total digit",key = BarcodeFormat.UPC_E,fragmentOpen = OpenSupportedCodesFragment::class.java))
        listAddQRCode.add(Supported("UPC A", R.drawable.barcode1,"11 digits + 1 total digit",key = BarcodeFormat.UPC_A,fragmentOpen = OpenSupportedCodesFragment::class.java))
        listAddQRCode.add(Supported("Code 128", R.drawable.barcode1,"Text without special characters",key = BarcodeFormat.CODE_128,fragmentOpen = OpenSupportedCodesFragment::class.java))
        listAddQRCode.add(Supported("Code 93", R.drawable.barcode1,"Capitalized text has no special characters",key = BarcodeFormat.CODE_93,fragmentOpen = OpenSupportedCodesFragment::class.java))
        listAddQRCode.add(Supported("Code 39", R.drawable.barcode1,"Capitalized text has no special characters",key = BarcodeFormat.CODE_39,fragmentOpen = OpenSupportedCodesFragment::class.java))
        listAddQRCode.add(Supported("Codabar", R.drawable.barcode1,"Number",key = BarcodeFormat.CODABAR,fragmentOpen = OpenSupportedCodesFragment::class.java))
        listAddQRCode.add(Supported("ITF", R.drawable.barcode1,"Limit the number of digits",key = BarcodeFormat.ITF,fragmentOpen = OpenSupportedCodesFragment::class.java))

        listSpLiveData.postValue(listAddQRCode)
    }
}
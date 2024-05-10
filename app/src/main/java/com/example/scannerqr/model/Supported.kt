package com.example.scannerqr.model

import com.google.zxing.BarcodeFormat

data class Supported(
    var title: String = "",
    val icon: Int = 0,
    var content: String = "",
    val key : BarcodeFormat = BarcodeFormat.QR_CODE,
    val fragmentOpen: Class<*>? = null
)
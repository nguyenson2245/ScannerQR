package com.example.scannerqr.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.scan.scannerqr.R

class DialogCreateQr(context: Context) : Dialog(context) {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(context.getDrawable(R.drawable.bg_dialog))
        setContentView(R.layout.dialog_creat_qr)
    }
}
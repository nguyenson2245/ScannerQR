package com.example.scannerqr.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.scan.scannerqr.R

abstract class BaseDialog<VB : ViewBinding>(context: Context) : Dialog(context) {

    private lateinit var binding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_dialog))
        binding = getViewBinding(LayoutInflater.from(context))
        setContentView(binding.root)
    }

    abstract fun getViewBinding(inflater: LayoutInflater): VB
    abstract fun init()
    abstract fun initData()
    abstract fun initAction()
}
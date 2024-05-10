package com.example.scannerqr.ui

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import com.example.scannerqr.ui.mainfragment.MainFragment
import com.example.scannerqr.ui.qr.detail.DetailFragment
import com.example.scanqr.ui.splash.SplashFragment
import com.example.socialmedia.base.BaseActivity
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    private val viewModel : MainViewModel by viewModels()

    override fun init() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S_V2)
            openFragment(SplashFragment::class.java, null, false)
        else
            openFragment(MainFragment::class.java, null, false)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(android.R.id.content)
        when (fragment) {
            is DetailFragment -> {
               if (fragment.isBackScannerQR){
                   viewModel.isPlayCamera.postValue(true)
               }
            }
            else -> {

            }
        }
        super.onBackPressed()

    }
}

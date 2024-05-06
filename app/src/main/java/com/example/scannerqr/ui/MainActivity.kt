package com.example.scannerqr.ui

import android.os.Build
import android.view.LayoutInflater
import com.example.scanqr.ui.mainfragment.MainFragment
import com.example.scanqr.ui.splash.SplashFragment
import com.example.socialmedia.base.BaseActivity
import com.scan.scannerqr.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun init() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S_V2)
            openFragment(SplashFragment::class.java, null, false)
        else
            openFragment(MainFragment::class.java, null, false)
    }
}
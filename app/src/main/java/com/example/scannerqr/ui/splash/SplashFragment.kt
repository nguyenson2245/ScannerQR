package com.example.scanqr.ui.splash

import androidx.fragment.app.viewModels
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater

import com.example.scannerqr.ui.mainfragment.MainFragment
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.scan.scannerqr.databinding.FragmentSplashBinding

class SplashFragment : BaseFragmentWithBinding<FragmentSplashBinding>() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private val viewModel: SplashViewModel by viewModels()


    override fun getViewBinding(inflater: LayoutInflater): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater)
    }

    override fun init() {
        Handler(Looper.getMainLooper()).postDelayed({
            openFragment(MainFragment::class.java, null, false)
        }, 2000)
    }

    override fun initData() {

    }

    override fun initAction() {

    }
}
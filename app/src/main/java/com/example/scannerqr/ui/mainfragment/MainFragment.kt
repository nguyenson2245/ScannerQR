package com.example.scanqr.ui.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.example.scannerqr.ui.creatqr.CreateQrFragment
import com.example.scannerqr.ui.history.HistoryFragment
import com.example.scannerqr.ui.mainfragment.PagerAdapter
import com.example.scannerqr.ui.setting.SettingsFragment
import com.example.scanqr.ui.qr.QrcodeFragment
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentMainBinding


class MainFragment : BaseFragmentWithBinding<FragmentMainBinding>() {
    val listFragment: ArrayList<Fragment> = arrayListOf(
        QrcodeFragment.newInstance(),
        CreateQrFragment.newInstance(),
        HistoryFragment.newInstance(),
        SettingsFragment.newInstance()
    )

    companion object {
        fun newInstance() = MainFragment()
    }


    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: PagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater)
    }

    override fun init() {
        adapter = PagerAdapter(childFragmentManager)
        adapter.setData(listFragment)
        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = 4
        
    }


    override fun initData() {

    }

    override fun initAction() {
        binding.bottomNavigation.setOnItemSelectedListener() {
            binding.viewPager.currentItem = when (it.itemId) {
                R.id.scanqr -> 0
                R.id.create_qr -> 1
                R.id.history -> 2
                R.id.settings -> 3
                else -> 0
            }
            true
        }
        binding.viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }
            override fun onPageSelected(position: Int) {
                binding.bottomNavigation.selectedItemId = when (position) {
                    0 -> R.id.scanqr
                    1 -> R.id.create_qr
                    2 -> R.id.history
                    3 -> R.id.settings
                    else -> R.id.scanqr
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }
}
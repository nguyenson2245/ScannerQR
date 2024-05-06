package com.example.scannerqr.ui.mainfragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.scanqr.ui.qr.QrcodeFragment

class PagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {
    private var listFragment : ArrayList<Fragment> = arrayListOf()
    override fun getCount(): Int {
        return  listFragment.size
    }

    override fun getItem(position: Int): Fragment {
        return  listFragment.get(position)
    }
    fun setData(data: ArrayList<Fragment>){
        listFragment = data
        notifyDataSetChanged()
    }
}
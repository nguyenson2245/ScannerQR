package com.example.scannerqr.ui.qr.help.supportedCodes

import androidx.lifecycle.MutableLiveData
import com.example.scannerqr.model.Help
import com.example.socialmedia.base.BaseViewModel
import com.scan.scannerqr.R

class SupportedCodesViewModel : BaseViewModel(){

    private val listHelp: ArrayList<Help> = arrayListOf()
    val listLiveData: MutableLiveData<ArrayList<Help>> = MutableLiveData()

    fun initDataHelp() {
        listHelp.add((Help(R.drawable.item1)))
        listHelp.add((Help(R.drawable.item2)))
        listHelp.add((Help(R.drawable.item3)))
        listHelp.add((Help(R.drawable.item4)))
        listHelp.add((Help(R.drawable.item5)))
        listHelp.add((Help(R.drawable.item6)))
        listHelp.add((Help(R.drawable.item7)))
        listHelp.add((Help(R.drawable.item8)))
        listHelp.add((Help(R.drawable.item9)))
        listHelp.add((Help(R.drawable.item10)))
        listHelp.add((Help(R.drawable.item11)))
        listHelp.add((Help(R.drawable.item12)))
        listHelp.add((Help(R.drawable.item13)))
        listHelp.add((Help(R.drawable.item14)))
        listHelp.add((Help(R.drawable.item15)))
        listHelp.add((Help(R.drawable.item16)))
        listHelp.add((Help(R.drawable.item17)))
        listHelp.add((Help(R.drawable.item18)))

        listLiveData.postValue(listHelp)
    }
}
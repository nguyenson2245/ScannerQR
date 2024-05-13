package com.example.scannerqr.ui.qr.help.scanningTips

import androidx.lifecycle.MutableLiveData
import com.example.scannerqr.model.Help
import com.example.socialmedia.base.BaseViewModel
import com.scan.scannerqr.R

class ScanningTipsViewModel : BaseViewModel(){

    private val listHelp: ArrayList<Help> = arrayListOf()
    val listLiveData: MutableLiveData<ArrayList<Help>> = MutableLiveData()

    fun initDataHelp() {
        listHelp.add((Help(R.drawable.item21)))
        listHelp.add((Help(R.drawable.item22)))
        listHelp.add((Help(R.drawable.item23)))
        listHelp.add((Help(R.drawable.item24)))
        listHelp.add((Help(R.drawable.item25)))
        listHelp.add((Help(R.drawable.item26)))
        listHelp.add((Help(R.drawable.item27)))


        listLiveData.postValue(listHelp)
    }
}
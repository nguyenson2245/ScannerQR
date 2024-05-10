package com.example.scannerqr.ui.qr.detail

import androidx.lifecycle.MutableLiveData
import com.example.scannerqr.model.Detail
import com.example.socialmedia.base.BaseViewModel
import com.scan.scannerqr.R

class DetailViewModel : BaseViewModel() {
    private val listDetails: ArrayList<Detail> = arrayListOf()
    val listDetailsLiveData: MutableLiveData<ArrayList<Detail>> = MutableLiveData()


    fun initDataApp(type: Int) {
        listDetails.add(Detail("See code", R.drawable.seecode))
        when (type) {
            0 -> {
                listDetails.add(Detail("Wifi connection", R.drawable.wifi))
                listDetails.add(Detail("Copy password", R.drawable.copypw))
                listDetails.add(Detail("Copy the network name", R.drawable.copypw))
            }

            1 -> {
                listDetails.add(Detail("Open the website", R.drawable.openweb))
                listDetails.add(Detail("Dial 0987654321", R.drawable.phone))
                listDetails.add(Detail("Add contact", R.drawable.ic_contact))
            }

            2 -> {
                listDetails.add(Detail("Search for products on the web", R.drawable.web))
                listDetails.add(Detail("Search on Amazon.com", R.drawable.amazon))
                listDetails.add(Detail(" Search on eBay.com", R.drawable.ic_share))
            }

            3 -> {

                listDetails.add(Detail("Search the web", R.drawable.search))
            }

            4 -> {


                listDetails.add(Detail("Add to calendar", R.drawable.add2))
            }

            5 -> {
                listDetails.add(Detail("Show location", R.drawable.showslocation))

            }
        }








        listDetailsLiveData.postValue(listDetails)
    }
}
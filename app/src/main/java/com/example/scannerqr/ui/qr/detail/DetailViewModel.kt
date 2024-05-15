package com.example.scannerqr.ui.qr.detail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.scannerqr.model.Detail
import com.example.scannerqr.model.History
import com.example.scannerqr.repository.Repository
import com.example.socialmedia.base.BaseViewModel
import com.scan.scannerqr.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : BaseViewModel() {
    private val listDetails: ArrayList<Detail> = arrayListOf()
    private val repository= Repository()
    val listDetailsLiveData: MutableLiveData<ArrayList<Detail>> = MutableLiveData()


    fun initDataApp(type: Int,value: String) {
        listDetails.add(Detail("See code", R.drawable.seecode))
        when (type) {
            0 -> {
                listDetails.add(Detail("Wifi connection", R.drawable.wifi))
                listDetails.add(Detail("Copy password", R.drawable.copypw))
                listDetails.add(Detail("Copy the network name", R.drawable.copypw))
            }

            1 -> {
                listDetails.add(Detail("Open the website", R.drawable.openweb))
                listDetails.add(Detail("Dial $value", R.drawable.phone))
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
    fun addHistory(context: Context,history: History){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHistory(context,history)
        }
    }

    fun deleteHistory(context: Context,history: History){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHistory(context,history)
        }
    }
}
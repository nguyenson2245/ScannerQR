package com.example.scannerqr.ui.qr.detail

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.scannerqr.model.Detail
import com.example.scannerqr.model.History
import com.example.scannerqr.repository.Repository
import com.example.scanqr.ui.qr.TypeValue
import com.example.socialmedia.base.BaseViewModel
import com.scan.scannerqr.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : BaseViewModel() {
    private val listDetails: ArrayList<Detail> = arrayListOf()
    private val repository = Repository()
    val listDetailsLiveData: MutableLiveData<ArrayList<Detail>> = MutableLiveData()


    fun initDataApp(type: TypeValue, value: String) {
        listDetails.add(Detail("See code", R.drawable.seecode))
        when (type) {
            TypeValue.TYPE_WIFI -> {
                listDetails.add(Detail("Wifi connection", R.drawable.wifi))
                listDetails.add(Detail("Copy password", R.drawable.copypw))
                listDetails.add(Detail("Copy the network name", R.drawable.copypw))
            }

            TypeValue.TYPE_VCARD -> {
                listDetails.add(Detail("Open the website", R.drawable.openweb))
                listDetails.add(Detail("Dial ${getTelephoneFromVCard(value)}", R.drawable.phone))
                listDetails.add(Detail("Add contact", R.drawable.ic_contact))
            }

            TypeValue.TYPE_WEB -> {
                listDetails.add(Detail("Search for products on the web", R.drawable.web))
                listDetails.add(Detail("Search on Amazon.com", R.drawable.amazon))
                listDetails.add(Detail(" Search on eBay.com", R.drawable.ic_share))
            }

            TypeValue.TYPE_CONTENT -> {
                listDetails.add(Detail("Search the web", R.drawable.search))
            }

            TypeValue.TYPE_EVENT -> {
                listDetails.add(Detail("Add to calendar", R.drawable.add2))
            }

            TypeValue.TYPE_MAIL -> {

            }
            TypeValue.TYPE_SMS -> {

            }
        }
        listDetailsLiveData.postValue(listDetails)
    }

    fun addHistory(context: Context, history: History) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHistory(context, history)
        }
    }

    private fun getTelephoneFromVCard(vCardData: String): String? {
        val parsedData = parseVCard(vCardData)
        return parsedData["Telephone"]
    }

    fun getValueType(type: TypeValue, content: String): String {
        return when (type) {
            TypeValue.TYPE_WIFI -> mapToString(parseWifiQR(content))
            TypeValue.TYPE_CONTENT -> content
            TypeValue.TYPE_VCARD -> mapToString(parseVCard(content))
            TypeValue.TYPE_EVENT -> mapToString(parseEvent(content))
            TypeValue.TYPE_WEB -> mapToString(parseURL(content))
            TypeValue.TYPE_MAIL -> mapToString(parseEmail(content))
            TypeValue.TYPE_SMS -> mapToString(parseSMS(content))
        }
    }

    private fun parseWifiQR(contents: String): Map<String, String> {
        val data = mutableMapOf<String, String>()
        val fields = contents.substring(5).split(";")

        for (field in fields) {
            val parts = field.split(":")
            if (parts.size == 2) {
                when (parts[0]) {
                    "T" -> data["Type"] = parts[1]
                    "S" -> data["SSID"] = parts[1]
                    "P" -> data["Password"] = parts[1]
                    "H" -> data["Hidden"] = parts[1]
                }
            }
        }
        return data
    }


    private fun parseURL(contents: String): Map<String, String> {
        return mapOf("URL" to contents)
    }


    private fun parseEvent(contents: String): Map<String, String> {
        val data = mutableMapOf<String, String>()
        val lines = contents.split("\n")

        for (line in lines) {
            val parts = line.split(":")
            if (parts.size == 2) {
                when (parts[0]) {
                    "SUMMARY" -> data["Summary"] = parts[1]
                    "DTSTART" -> data["Start Date"] = parts[1]
                    "DTEND" -> data["End Date"] = parts[1]
                    "LOCATION" -> data["Location"] = parts[1]
                    "DESCRIPTION" -> data["Description"] = parts[1]
                }
            }
        }
        return data
    }

    fun parseVCard(vCardData: String): Map<String, String> {
        val vCardFields = mutableMapOf<String, String>()

        val lines = vCardData.trim().split("\n")
        var propertyName = ""
        var propertyValue = ""

        for (line in lines) {
            if (line.startsWith("BEGIN:VCARD") || line.startsWith("END:VCARD")) {
                continue
            }

            val separatorIndex = line.indexOf(":")
            if (separatorIndex != -1) {
                propertyName = line.substring(0, separatorIndex)
                propertyValue = line.substring(separatorIndex + 1)
                vCardFields[propertyName] = propertyValue
            }
        }

        return vCardFields
    }


    private fun parseEmail(contents: String): Map<String, String> {
        return mapOf("Email" to contents.substring(7))
    }

    // Phân tích mã QR SMS
    private fun parseSMS(contents: String): Map<String, String> {
        val data = mutableMapOf<String, String>()
        val smsData = contents.substring(6).split(":")

        if (smsData.size == 2) {
            data["Phone Number"] = smsData[0]
            data["Message"] = smsData[1]
        } else if (smsData.size == 1) {
            data["Phone Number"] = smsData[0]
        }
        return data
    }


    fun deleteHistory(context: Context, history: History) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHistory(context, history)
        }
    }

    fun mapToString(map: Map<String, String>): String {
        val stringBuilder = StringBuilder()
        for ((key, value) in map) {
            stringBuilder.append("$key: $value\n")
        }
        return stringBuilder.toString()
    }
}
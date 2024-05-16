package com.example.scannerqr.ui.setting

import androidx.lifecycle.MutableLiveData
import com.example.scannerqr.application.MainApp
import com.example.scannerqr.local.Preferences
import com.example.scannerqr.model.Settings
import com.example.scannerqr.ui.utils.Constants
import com.example.socialmedia.base.BaseViewModel
import com.scan.scannerqr.R

class SettingsViewModel : BaseViewModel() {

    private val listSettings: ArrayList<Settings> = arrayListOf()
    val listAddSettings: MutableLiveData<ArrayList<Settings>> = MutableLiveData()

    val preferences = Preferences.getInstance(MainApp.newInstance())

    fun initDataSetting() {
        listSettings.clear()
        listSettings.add(Settings(title = "General", icon = R.drawable.ic_general))
        listSettings.add(Settings(title = "Display", description = "System default options"))
        listSettings.add(Settings(title = "Help and Feedback"))
        listSettings.add(Settings(title = "Scan control", icon = R.drawable.ic_control))

        listSettings.add(
            Settings(
                title = "Open web pages automatically",
                showButtonSwitch = true,
                switchEnabled = preferences.getBoolean(Constants.OPEN_WEB) ?: false,
                key = Constants.OPEN_WEB
            )
        )
        listSettings.add(
            Settings(
                title = "Scan continuously",
                description = "Only add scans to history",
                showButtonSwitch = true,
                switchEnabled = preferences.getBoolean(Constants.SCAN) ?: false,   key = Constants.SCAN
            )
        )
        listSettings.add(
            Settings(
                title = "Duplicate barcodes",
                description = "Save duplicate barcodes in history",
                showButtonSwitch = true,
                switchEnabled = preferences.getBoolean(Constants.DUPLICATION) ?: false,   key = Constants.DUPLICATION
            )
        )
        listSettings.add(
            Settings(
                title = "Confirm manual scan",
                description = "Avoid accidental scanning",
                showButtonSwitch = true,
                switchEnabled = preferences.getBoolean(Constants.CONFIRM) ?: false,   key = Constants.CONFIRM
            )
        )
        listSettings.add(
            Settings(
                title = "Sound",
                showButtonSwitch = true,
                switchEnabled = preferences.getBoolean(Constants.SOUND) ?: false,   key = Constants.SOUND
            )
        )

        listSettings.add(
            Settings(
                title = "Vibrate",
                showButtonSwitch = true,
                switchEnabled = preferences.getBoolean(Constants.VIBRATE) ?: false,   key = Constants.VIBRATE
            )
        )

        listSettings.add(
            Settings(
                title = "Copy to clipboard",
                showButtonSwitch = true,
                switchEnabled = preferences.getBoolean(Constants.COPY) ?: false,   key = Constants.COPY
            )
        )

        listSettings.add(Settings(title = "Product barcode", icon = R.drawable.ic_settings_barcode))

        listSettings.add(
            Settings(
                title = "Country to search for products",
                description = "System default options"
            )
        )
        listSettings.add(
            Settings(
                title = "Product information",
                description = "Display product information and price if available ",
                showButtonSwitch = true, switchEnabled =  preferences.getBoolean(Constants.PRODUCT) ?: false,   key = Constants.PRODUCT
            )
        )
        listSettings.add(Settings(title = "Introduce", icon = R.drawable.ic_introduce))
        listSettings.add(Settings(title = "Introduce"))
        listSettings.add(Settings(title = "Open source license"))
        listSettings.add(Settings(title = "Privacy policy"))
        listSettings.add(Settings(title = "App version", description = "Version 1.1"))

        listAddSettings.postValue(listSettings)
    }

    fun saveSettingKey(key: String, switchEnabled: Boolean) {
      preferences.setBoolean(key,switchEnabled)
    }


}
package com.example.scannerqr.ui.setting

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.scannerqr.application.MainApp
import com.example.scannerqr.local.Preferences
import com.example.scannerqr.model.Settings
import com.example.scannerqr.ui.qr.help.HelpAndFeedbackFragment
import com.example.scannerqr.ui.setting.introduce.IntroduceFragment
import com.example.scannerqr.ui.setting.privacyPolicy.PrivacyPolicyFragment
import com.example.scannerqr.ui.utils.Constants
import com.example.socialmedia.base.BaseViewModel
import com.scan.scannerqr.R

class SettingsViewModel : BaseViewModel() {

    private val listSettings: ArrayList<Settings> = arrayListOf()
    val listAddSettings: MutableLiveData<ArrayList<Settings>> = MutableLiveData()

    val preferences = Preferences.getInstance(MainApp.newInstance())

    fun initDataSetting(context: Context) {
        listSettings.clear()
        listSettings.add(Settings(title = "General", icon = R.drawable.ic_general))
        listSettings.add(Settings(title = "Help and Feedback", fragmentOpen =  HelpAndFeedbackFragment::class.java))
        listSettings.add(Settings(title = "Scan control", icon = R.drawable.ic_control))

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

        listSettings.add(Settings(title = "Introduce", icon = R.drawable.ic_introduce, action = {
            Toast.makeText(context, "Functionality is currently under development", Toast.LENGTH_SHORT).show()
        }))
        listSettings.add(Settings(title = "Introduce", fragmentOpen =  IntroduceFragment::class.java))
        listSettings.add(Settings(title = "Privacy policy", fragmentOpen =  PrivacyPolicyFragment::class.java))

        listSettings.add(Settings(title = "App version", description = "Version 1.1"))

        listAddSettings.postValue(listSettings)
    }

    fun saveSettingKey(key: String, switchEnabled: Boolean) {
      preferences.setBoolean(key,switchEnabled)
    }


}
package com.example.scannerqr.application

import android.app.Application
import com.example.scannerqr.local.Preferences

class MainApp : Application() {

    var pref: Preferences? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        pref = Preferences.getInstance(this)
        if (pref?.firstInstall == false) {
            pref?.firstInstall = true
            pref?.setValueCoin(10)
        }
    }

    companion object {
        var instance: MainApp? = null
        fun newInstance(): MainApp {
            if (instance == null) {
                instance = MainApp()
            }
            return instance!!
        }

        fun getContext() = instance?.applicationContext!!
    }
}
package com.example.scannerqr.ui.setting

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.example.scannerqr.model.Settings
import com.example.socialmedia.base.recyclerview.BaseRecyclerAdapter
import com.example.socialmedia.base.recyclerview.BaseViewHolder
import com.scan.scannerqr.R

class SettingsAdapter : BaseRecyclerAdapter<Settings, SettingsAdapter.SettingsViewHolder>() {
inner class SettingsViewHolder(val binding: ViewDataBinding) : BaseViewHolder<Settings>(binding){

}

    override fun getItemLayoutResource(viewType: Int): Int {
        return  R.layout.item_settings
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
       return SettingsViewHolder(getViewHolderDataBinding(parent, viewType))
    }
}
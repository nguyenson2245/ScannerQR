package com.example.scannerqr.ui.setting

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.scannerqr.model.Settings
import com.example.socialmedia.base.recyclerview.BaseRecyclerAdapter
import com.example.socialmedia.base.recyclerview.BaseViewHolder
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.ItemSettingContentBinding
import com.scan.scannerqr.databinding.ItemSettingSwitchBinding
import com.scan.scannerqr.databinding.ItemSettingsBinding

class SettingsAdapter : BaseRecyclerAdapter<Settings, SettingsAdapter.SettingsViewHolder>() {
    inner class SettingsViewHolder(val binding: ViewDataBinding) :
        BaseViewHolder<Settings>(binding) {
        override fun bind(itemData: Settings?) {
            super.bind(itemData)
            if (binding is ItemSettingContentBinding) {
                binding.title.text = itemData?.title
                binding.content.text = itemData?.description
            }
            if (binding is ItemSettingsBinding) {
                binding.title.text = itemData?.title
              Glide.with(itemView).load(itemData?.icon).into(binding.imageView)
            }
            if (binding is ItemSettingSwitchBinding) {
                binding.title.text = itemData?.title
                binding.content.text = itemData?.description
                binding.btnSwitch.visibility =
                    if (itemData?.showButtonSwitch == true) View.VISIBLE else View.GONE
                binding.btnSwitch.isEnabled = itemData?.switchEnabled ?: false
            }
        }
    }

    override fun getItemLayoutResource(viewType: Int): Int {
        return when (viewType) {
            TYPE_CONTENT -> R.layout.item_setting_content
            TYPE_CONTENT_SWITCH -> R.layout.item_setting_switch
            else -> R.layout.item_settings
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = listItem[position]
        return if (item.description.isNotEmpty() && !item.showButtonSwitch) {
            TYPE_CONTENT
        } else if (item.showButtonSwitch) {
            TYPE_CONTENT_SWITCH
        } else if (item.title.isNotEmpty()) {
            TYPE_HEADER
        }else 4

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        return SettingsViewHolder(getViewHolderDataBinding(parent, viewType))
    }

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_CONTENT = 1
        const val TYPE_CONTENT_SWITCH = 3
    }
}
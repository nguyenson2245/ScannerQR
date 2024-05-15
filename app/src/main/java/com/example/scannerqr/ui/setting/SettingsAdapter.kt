package com.example.scannerqr.ui.setting

import android.media.MediaPlayer
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.scannerqr.model.Settings
import com.example.scannerqr.ui.utils.Constants
import com.example.socialmedia.base.recyclerview.BaseRecyclerAdapter
import com.example.socialmedia.base.recyclerview.BaseViewHolder
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.ItemSettingContentBinding
import com.scan.scannerqr.databinding.ItemSettingSwitchBinding
import com.scan.scannerqr.databinding.ItemSettingsBinding


class SettingsAdapter(val viewModel: SettingsViewModel) :
    BaseRecyclerAdapter<Settings, SettingsAdapter.SettingsViewHolder>() {

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

                binding.imageView.visibility =
                    if (itemData?.icon != 0 && itemData?.icon != null) View.VISIBLE else View.GONE

                Glide.with(itemView).load(itemData?.icon).into(binding.imageView)
            }
            if (binding is ItemSettingSwitchBinding) {
                binding.title.text = itemData?.title
                binding.content.text = itemData?.description

                binding.content.visibility =
                    if (itemData?.description?.isNotEmpty() == true) View.VISIBLE else View.GONE

                binding.btnSwitch.visibility =
                    if (itemData?.showButtonSwitch == true) View.VISIBLE else View.GONE
                binding.btnSwitch.isChecked = itemData?.switchEnabled ?: false

                binding.btnSwitch.setOnClickListener {
                    if (itemData?.key == Constants.OPEN_WEB) {

                        if (itemData?.switchEnabled != true) {
                            Toast.makeText(itemView.context, "OPEN_WEB", Toast.LENGTH_SHORT).show()

                        } else {
                            Toast.makeText(itemView.context, "OFF", Toast.LENGTH_SHORT).show()

                        }
                    } else if (itemData?.key == Constants.OPEN_WEB) {
                        if (itemData?.switchEnabled != true) {
                            Toast.makeText(itemView.context, "On", Toast.LENGTH_SHORT).show()

                        } else {
                            Toast.makeText(itemView.context, "OFF", Toast.LENGTH_SHORT).show()

                        }
                    } else if (itemData?.key == Constants.SCAN) {
                        if (itemData?.switchEnabled != true) {
                            Toast.makeText(itemView.context, "On", Toast.LENGTH_SHORT).show()

                        } else {
                            Toast.makeText(itemView.context, "OFF", Toast.LENGTH_SHORT).show()

                        }
                    } else if (itemData?.key == Constants.DUPLICATION) {
                        if (itemData?.switchEnabled != true) {
                            Toast.makeText(itemView.context, "On", Toast.LENGTH_SHORT).show()

                        } else {
                            Toast.makeText(itemView.context, "OFF", Toast.LENGTH_SHORT).show()

                        }

                    } else if (itemData?.key == Constants.CONFIRM) {

                        if (itemData?.switchEnabled != true) {
                            Toast.makeText(itemView.context, "On", Toast.LENGTH_SHORT).show()

                        } else {
                            Toast.makeText(itemView.context, "OFF", Toast.LENGTH_SHORT).show()

                        }

                    } else if (itemData?.key == Constants.SOUND) {
                        val mediaPlayer: MediaPlayer =
                            MediaPlayer.create(itemView.context, R.raw.musicmp3)
                        if (itemData?.switchEnabled != true) {
                            Toast.makeText(itemView.context, "On", Toast.LENGTH_SHORT)
                                .show()
                            mediaPlayer.start();
                        } else {
                            Toast.makeText(itemView.context, "OFF", Toast.LENGTH_SHORT)
                                .show()
                            mediaPlayer.pause();
                            mediaPlayer.seekTo(0);
                        }

                    } else if (itemData?.key == Constants.PRODUCT) {
                        if (itemData?.switchEnabled != true) {
                            Toast.makeText(itemView.context, "On", Toast.LENGTH_SHORT).show()

                        } else {
                            Toast.makeText(itemView.context, "OFF", Toast.LENGTH_SHORT).show()

                        }
                    } else {
                        Log.d("switchEnabled", "err")
                    }

                    viewModel.saveSettingKey(itemData?.key ?: "", itemData?.switchEnabled != true)
                    viewModel.preferences.setBoolean(
                        itemData?.key ?: "",
                        itemData?.switchEnabled != true
                    )
                    viewModel.initDataSetting()
                }
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
        } else 4

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
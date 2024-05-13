package com.example.scannerqr.ui.qr.help

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.scannerqr.model.CreateQR
import com.example.scannerqr.model.Help
import com.example.socialmedia.base.recyclerview.BaseRecyclerAdapter
import com.example.socialmedia.base.recyclerview.BaseViewHolder
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.ItemHelpBinding

class HelpAdapter() : BaseRecyclerAdapter<Help, HelpAdapter.HelpViewHolder>(){
    inner class HelpViewHolder(val binding : ViewDataBinding) : BaseViewHolder<Help>(binding){
        override fun bind(itemData: Help?) {
            super.bind(itemData)
            if (binding is ItemHelpBinding){
                Glide.with(itemView).load(itemData?.icon).into(binding.icon)
            }
        }
    }

    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_help
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpViewHolder {
        return  HelpViewHolder(getViewHolderDataBinding(parent, viewType))
    }
}
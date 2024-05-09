package com.example.scannerqr.ui.creatqr.addQrCode.app

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.scannerqr.model.AddQRCode
import com.example.scannerqr.model.App
import com.example.scannerqr.model.CreateQR
import com.example.socialmedia.base.recyclerview.BaseRecyclerAdapter
import com.example.socialmedia.base.recyclerview.BaseViewHolder
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.ItemAppBinding
import com.scan.scannerqr.databinding.ItemCreateQrBinding

class AppAdapter(val click: (Class<*>) -> Unit) : BaseRecyclerAdapter<App,AppAdapter.CreateQrViewHolder>(){
    inner class CreateQrViewHolder(val binding : ViewDataBinding) : BaseViewHolder<App>(binding){
        override fun bind(itemData: App?) {
            super.bind(itemData)
            if (binding is ItemAppBinding){
                binding.title.text = itemData?.title
                Glide.with(itemView).load(itemData?.icon).into(binding.icon)
                onItemClickListener { itemData?.fragmentOpen?.let { click.invoke(it) } }
            }
        }
    }

    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_app
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateQrViewHolder {
        return  CreateQrViewHolder(getViewHolderDataBinding(parent, viewType))
    }
}
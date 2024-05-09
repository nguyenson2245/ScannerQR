package com.example.scannerqr.ui.creatqr.supportedCodes

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.scannerqr.model.Supported
import com.example.socialmedia.base.recyclerview.BaseRecyclerAdapter
import com.example.socialmedia.base.recyclerview.BaseViewHolder
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.ItemSupportedBinding

class SupportedCodeAdapter(val click: (Class<*>) -> Unit) : BaseRecyclerAdapter<Supported,SupportedCodeAdapter.CreateQrViewHolder>(){
    inner class CreateQrViewHolder(val binding : ViewDataBinding) : BaseViewHolder<Supported>(binding){
        override fun bind(itemData: Supported?) {
            super.bind(itemData)
            if (binding is ItemSupportedBinding){
                binding.title.text = itemData?.title
                binding.content.text = itemData?.content
                Glide.with(itemView).load(itemData?.icon).into(binding.icon)
                onItemClickListener { itemData?.fragmentOpen?.let { click.invoke(it) } }
            }
        }
    }

    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_supported
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateQrViewHolder {
        return  CreateQrViewHolder(getViewHolderDataBinding(parent, viewType))
    }
}
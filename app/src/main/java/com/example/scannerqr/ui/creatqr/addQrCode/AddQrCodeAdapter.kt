package com.example.scannerqr.ui.creatqr.addQrCode

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.scannerqr.model.AddQRCode
import com.example.scannerqr.model.CreateQR
import com.example.socialmedia.base.recyclerview.BaseRecyclerAdapter
import com.example.socialmedia.base.recyclerview.BaseViewHolder
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.ItemCreateQrBinding

class AddQrCodeAdapter(val click: (Class<*>) -> Unit) : BaseRecyclerAdapter<AddQRCode,AddQrCodeAdapter.CreateQrViewHolder>(){
    inner class CreateQrViewHolder(val binding : ViewDataBinding) : BaseViewHolder<AddQRCode>(binding){
        override fun bind(itemData: AddQRCode?) {
            super.bind(itemData)
            if (binding is ItemCreateQrBinding){
                binding.title.text = itemData?.title
                Glide.with(itemView).load(itemData?.icon).into(binding.icon)
                onItemClickListener { itemData?.fragmentOpen?.let { click.invoke(it) } }
            }
        }
    }

    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_create_qr
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateQrViewHolder {
        return  CreateQrViewHolder(getViewHolderDataBinding(parent, viewType))
    }
}
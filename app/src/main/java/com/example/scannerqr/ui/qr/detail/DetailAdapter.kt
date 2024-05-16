package com.example.scannerqr.ui.qr.detail

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.scannerqr.model.CreateQR
import com.example.scannerqr.model.Detail
import com.example.socialmedia.base.recyclerview.BaseRecyclerAdapter
import com.example.socialmedia.base.recyclerview.BaseViewHolder
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.ItemCreateQrBinding
import com.scan.scannerqr.databinding.ItemDetailBinding

class DetailAdapter(val click: (Class<*>) -> Unit) :
    BaseRecyclerAdapter<Detail, DetailAdapter.DetailViewHolder>() {
    inner class DetailViewHolder(val binding: ViewDataBinding) : BaseViewHolder<Detail>(binding) {
        override fun bind(itemData: Detail?) {
            super.bind(itemData)
            if (binding is ItemDetailBinding) {
                binding.title.text = itemData?.title
                Glide.with(itemView).load(itemData?.icon).into(binding.imageView)
                onItemClickListener { itemData?.fragmentOpen?.let { click.invoke(it) } }
            }
            onItemClickListener {
                itemData?.action?.invoke()
            }
        }
    }

    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_detail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(getViewHolderDataBinding(parent, viewType))
    }

}
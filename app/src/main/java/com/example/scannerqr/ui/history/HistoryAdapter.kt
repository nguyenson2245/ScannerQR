package com.example.scannerqr.ui.history

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.scannerqr.model.History
import com.example.socialmedia.base.recyclerview.BaseRecyclerAdapter
import com.example.socialmedia.base.recyclerview.BaseViewHolder
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.ItemHistoryBinding

class HistoryAdapter(val click: (History) -> Unit) :
    BaseRecyclerAdapter<History, HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(val binding : ViewDataBinding) : BaseViewHolder<History>(binding){
        override fun bind(itemData: History?) {
            super.bind(itemData)
            if (binding is ItemHistoryBinding){

                binding.txtTitle.text = itemData?.title
                binding.txtDate.text = itemData?.date
                binding.txtTime.text = itemData?.time

                Glide.with(itemView).load(itemData?.icon).into(binding.icon)
                onItemClickListener {
                    if (itemData != null) {
                        click.invoke(itemData)
                    }
                }
            }
        }
    }
    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_history
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.HistoryViewHolder {
        return  HistoryViewHolder(getViewHolderDataBinding(parent, viewType))
    }
}
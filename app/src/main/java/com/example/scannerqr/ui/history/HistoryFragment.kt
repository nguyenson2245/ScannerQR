package com.example.scannerqr.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.model.History
import com.example.scannerqr.ui.qr.detail.DetailFragment
import com.example.scanqr.ui.qr.QrcodeFragment
import com.example.socialmedia.base.utils.gone
import com.example.socialmedia.base.utils.visible
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentHistoryBinding


class HistoryFragment : BaseFragmentWithBinding<FragmentHistoryBinding>() {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var adapter: HistoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentHistoryBinding {
        return FragmentHistoryBinding.inflate(inflater)
    }


    override fun init() {
        val dividerItemDecoration = DividerItemDecoration(
            context,
            LinearLayoutManager.VERTICAL
        )
        adapter = HistoryAdapter() {
            val bundle = Bundle()
            bundle.putString("value", it.title)
            bundle.putString("date", it.date)
            openFragment(DetailFragment::class.java, bundle, true)

        }
        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)
        binding.rvView.addItemDecoration(dividerItemDecoration)
    }

    override fun initData() {
        context?.let { viewModel.getLiveDateHistory(it).observe(viewLifecycleOwner){
            adapter.submitList(it)
            if (it.isEmpty()){
                binding.nodata.visible()
            }else{
                binding.nodata.gone()
            }

        } }

    }

    override fun initAction() {

    }


}
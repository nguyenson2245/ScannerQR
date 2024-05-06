package com.example.scannerqr.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.model.History
import com.example.scanqr.ui.qr.QrcodeFragment
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentHistoryBinding


class HistoryFragment : BaseFragmentWithBinding<FragmentHistoryBinding>() {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var adapter: HistoryAdapter
    private val listHistory: ArrayList<History> = arrayListOf()


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
            openFragment(it, null, true)
            toast(it.name)
        }
        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)
        binding.rvView.addItemDecoration(dividerItemDecoration)
    }

    override fun initData() {
        initDataCreateQr()
        adapter.submitList(listHistory)
    }

    override fun initAction() {

    }

    fun initDataCreateQr() {
        listHistory.add(
            History(
                0,
                "Hello",
                R.drawable.ic_edit,
                "13/5/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(
                0,
                "Bart Simpson",
                R.drawable.ic_edit,
                "23/6/4048",
                "10:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "03/2/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "13/1/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "3/5/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "13/7/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "13/5/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "13/5/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )
        listHistory.add(
            History(0,
                "Hello",
                R.drawable.ic_edit,
                "13/5/4048",
                "12:30",
                "Qr",
                QrcodeFragment::class.java
            )
        )

    }
}
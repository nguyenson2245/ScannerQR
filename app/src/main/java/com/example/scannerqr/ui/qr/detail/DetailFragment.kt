package com.example.scannerqr.ui.qr.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.model.History
import com.example.scannerqr.repository.Repository
import com.example.scannerqr.ui.MainViewModel
import com.example.scanqr.ui.qr.TypeValue
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.FragmentDetailBinding
import java.text.SimpleDateFormat
import java.util.Date

class DetailFragment : BaseFragmentWithBinding<FragmentDetailBinding>() {
    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: DetailAdapter
    private var value: String = ""
    var isBackScannerQR: Boolean = false
    private var date: String = ""
    private var type: TypeValue? = TypeValue.TYPE_CONTENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        value = arguments?.getString("value") ?: ""
        isBackScannerQR = arguments?.getBoolean("isBack") ?: false
        date = arguments?.getString("date") ?: ""
        type = (arguments?.getSerializable("type_va lue") ?: TypeValue.TYPE_CONTENT) as TypeValue?

    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater)
    }


    override fun init() {
        adapter = DetailAdapter() {
            openFragment(it, null, true)
        }

        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)
    }

    @SuppressLint("SimpleDateFormat")
    override fun initData() {
        type?.let { viewModel.initDataApp(it, value) }
        binding.time.text = if (date.isNullOrEmpty())
            SimpleDateFormat("dd/MM/yyyy HH:mm").format(Date(System.currentTimeMillis())) else date
        binding.title.text = value
        viewModel.listDetailsLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        if (isBackScannerQR) {
            context?.let {
                viewModel.addHistory(
                    it,
                    History(0, value, R.drawable.seecode, binding.time.text.toString())
                )
            }
        }
    }

    override fun initAction() {

        binding.toolbar.click {
            mainViewModel.isPlayCamera.postValue(true)
            onBackPressed()

        }

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.delete -> {
                   toast("delete")
                    true
                }
                R.id.outToTxt -> {
                    toast("outToTxt")
                    true
                }
                R.id.more -> {
                    toast("more")
                    true
                }
            }
            true
        }

    }

}
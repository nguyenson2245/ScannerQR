package com.example.scannerqr.ui.qr.detail

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    private var dataHistory: History? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isBackScannerQR = arguments?.getBoolean("isBack") ?: false

        value = arguments?.getString("value") ?: ""
        date = arguments?.getString("date") ?: ""

        dataHistory = arguments?.getSerializable("dataHistory") as History?
        type = (arguments?.getSerializable("type_value") ?: TypeValue.TYPE_CONTENT) as TypeValue?

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

        type?.let {
            viewModel.initDataApp(it, value)
            binding.title.text = viewModel.getValueType(it, value)
        }

        binding.time.text = if (date.isNullOrEmpty())
            SimpleDateFormat("dd/MM/yyyy HH:mm").format(Date(System.currentTimeMillis())) else date
        viewModel.listDetailsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        if (isBackScannerQR) {
            if (dataHistory == null) {
                dataHistory = History(0, value, R.drawable.seecode, binding.time.text.toString())
            }
            context?.let {
                viewModel.addHistory(
                    it, dataHistory!!

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
            when (it.itemId) {
                R.id.delete -> {

                    AlertDialog.Builder(requireActivity())
                        .setTitle("Delete entire history ? ")
                        .setMessage("Do you want to delete . -> $value")
                        .setPositiveButton("Yes") { dialog, which ->
                            dataHistory?.let { it1 ->
                                viewModel.deleteHistory(
                                    requireActivity(),
                                    it1
                                )
                            }
                            onBackPressed()
                        }
                        .setNegativeButton("No", null)
                        .show()

                    true
                }

                R.id.outToTxt -> {

                    val dataToShare = value
                    val shareIntent = Intent(Intent.ACTION_SEND)

                    shareIntent.setType("text/plain")
                    shareIntent.putExtra(Intent.EXTRA_TEXT, dataToShare)

                    startActivity(Intent.createChooser(shareIntent, "Share data with"))

                    true
                }
            }
            true
        }

    }

}
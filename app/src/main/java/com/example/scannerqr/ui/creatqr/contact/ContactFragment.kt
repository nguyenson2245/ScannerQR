package com.example.scannerqr.ui.creatqr.contact

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.socialmedia.base.utils.click
import com.scan.scannerqr.databinding.FragmentContactBinding

class ContactFragment : BaseFragmentWithBinding<FragmentContactBinding>() {

    companion object {
        fun newInstance() = ContactFragment()
    }

    private val viewModel: ContactViewModel by viewModels()


    override fun getViewBinding(inflater: LayoutInflater): FragmentContactBinding {
        return FragmentContactBinding.inflate(inflater)
    }


    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        binding.save.click {
            val vCardData = "Full Name:${binding.fullName.text}" + "\n" +
                    "Company:" + binding.company.text + "\n" +
                    "Title:" + binding.title.text + "\n" +
                    "Phone number:" + binding.phoneNumber.text + "\n" +
                    "email:" + binding.email.text + "\n" +
                    "Address:" + binding.address.text + "\n" +
                    "Zip:" + binding.zipCode.text + "\n" +
                    "Country:" + binding.city.text + "\n" +
                    "Region:" + binding.region.text + "\n" +
                    "Nation" + binding.nation.text + "\n" +
                    "Website:" + binding.web.text
            context?.let { it1 -> DialogCreateQr(it1, vCardData).show() }
        }
    }
}
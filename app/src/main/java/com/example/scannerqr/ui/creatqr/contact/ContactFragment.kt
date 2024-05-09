package com.example.scannerqr.ui.creatqr.contact

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Intent
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.socialmedia.base.utils.checkPermission
import com.example.socialmedia.base.utils.click
import com.permissionx.guolindev.PermissionX
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

    override fun onResume() {
        super.onResume()
    }

    override fun initData() {

    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val contactData = data?.data;

                val cursor = contactData?.let {
                    context?.getContentResolver()?.query(it, null, null, null, null)
                };
                cursor?.moveToFirst()
                val hasPhone =
                    cursor?.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                val contactId =
                    cursor?.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
                if (hasPhone == "1") {
                    val phones = context?.contentResolver?.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = $contactId",
                        null,
                        null
                    )
                    while (phones?.moveToNext() == true) {
                        var number =
                            phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        var name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                        binding.phoneNumber.setText(number.toString())
                        binding.fullName.setText(name.toString())
                    }
                    phones?.close()
                } else {
                    Toast.makeText(
                        context,
                        "This contact has no phone number",
                        Toast.LENGTH_LONG
                    ).show()
                }
                cursor?.close()
            }
        }
    }





    override fun initAction() {
        binding.contact.click {
            if (context?.checkPermission(Manifest.permission.READ_CONTACTS) == true) {
                val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                startActivityForResult(intent, 1)
            } else {
                PermissionX.init(this)
                    .permissions(Manifest.permission.READ_CONTACTS)
                    .request { allGranted, grantedList, deniedList ->
                        if (allGranted) {
                            Toast.makeText(context, "oke", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "These permissions are denied: $deniedList",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }

        }
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
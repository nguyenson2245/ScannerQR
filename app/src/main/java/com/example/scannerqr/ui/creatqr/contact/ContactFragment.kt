package com.example.scannerqr.ui.creatqr.contact

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.scannerqr.ui.dialog.DialogCreateQr
import com.example.socialmedia.base.utils.checkPermission
import com.example.socialmedia.base.utils.click
import com.google.zxing.BarcodeFormat
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
                        var name =
                            phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
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

        editText()

        binding.toolbar.click {
            onBackPressed()
        }

        binding.contact.click {
            if (context?.checkPermission(Manifest.permission.READ_CONTACTS) == true) {
                val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                startActivityForResult(intent, 1)
            } else {
                when {
                    context?.checkPermission(Manifest.permission.READ_CONTACTS) == false -> {
                        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 200)
                    }

                    shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 200)
                    }

                    else -> {
                        toast("showDialog")
                    }
                }

            }

        }
        binding.save.click {
            val fullName = binding.fullName.text.trim().toString()


            if (fullName.isNotEmpty() &&
                binding.fullName.error == null

            ) {
                context?.let { it1 ->
                    val vCardData = """
    BEGIN:VCARD
    Full name:${binding.fullName.text};${binding.title.text}
    Company:${binding.company.text}
    Telephone:${binding.phoneNumber.text}
    Email:${binding.email.text}
    Address:${binding.address.text}
    City:${binding.city.text}
    Region:${binding.region.text}
    Nation${binding.nation.text}
    ZipL${binding.zipCode.text}
    URL:${binding.web.text}
    END:VCARD
""".trimIndent()

                    context?.let { it1 ->
                        DialogCreateQr(
                            this@ContactFragment,
                            vCardData,
                            BarcodeFormat.QR_CODE
                        ).show()
                    }

                }
            } else
                if (fullName.isEmpty()

                ) {

                    toast("PLEASE enter complete information")
                }
        }


    }

    private fun editText() {

    }
}

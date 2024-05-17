package com.example.socialmedia.base.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.io.File

class SafeClickListener(
    private var defaultInterval: Long = 1000, private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}


fun View.click(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun View.showKeyboard(context: Context) {
    val imm: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard(activity: Activity) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.clearFocus(activity: Activity) {
    activity.currentFocus?.clearFocus()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.goneIf(goneIf: Boolean) {
    visibility = if (goneIf) View.GONE else View.VISIBLE
}

fun EditText.addAfterTextChangeAction(textChangeAction: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            textChangeAction(s?.toString().orEmpty())
        }
    })
}

fun Context.checkPermission(vararg permission: String): Boolean {
    permission.forEach {
        if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
            return false
        }
    }
    return true
}

fun Context.checkPermissionCamera(context: Context): Boolean {
    return context.checkPermission(Manifest.permission.CAMERA)
}

fun Int.dpToPx(resources: Resources): Int {
    val scale = resources.displayMetrics.density
    return (this * scale + 0.5f).toInt()
}
fun Context.doSendBroadcast(filePath: String) {
    val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(File(filePath)))
    sendBroadcast(intent)
}


package com.example.scannerqr.ui.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

object PermissionUtils {

    fun Context.checkPermission(vararg permission: String): Boolean {
        permission.forEach {
            if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    fun checkStoragePermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.checkPermission(Manifest.permission.READ_MEDIA_VIDEO)
        } else context.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    fun checkPermissionCamera(context: Context): Boolean {
        return context.checkPermission(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    }

    fun checkPermissionWriteStore(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            context.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        } else true
    }

    val listPR = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) listOf<String>(Manifest.permission.READ_MEDIA_VIDEO) else
            listOf<String>(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

}
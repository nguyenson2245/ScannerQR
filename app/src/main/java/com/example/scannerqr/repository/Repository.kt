package com.example.scannerqr.repository

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.example.scannerqr.local.AppDatabase
import com.example.scannerqr.model.History
import com.example.scannerqr.model.ImageModel
import com.example.socialmedia.common.State

class Repository {
    fun getAllImages(context: Context, callback: (state: State<ArrayList<ImageModel>>) -> Unit) {
        callback.invoke(State.Loading)
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor: Cursor?
        val column_index_data: Int
        val listOfAllImages = ArrayList<ImageModel>()
        var absolutePathOfImage: String? = null

        val projection =
            arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

        cursor = context.contentResolver!!.query(uri, projection, null, null, null)

        column_index_data = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        cursor!!
            .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        while (cursor!!.moveToNext()) {
            absolutePathOfImage = cursor!!.getString(column_index_data)
            listOfAllImages.add(ImageModel(absolutePathOfImage))
        }
        callback.invoke(State.Success(listOfAllImages))
    }
    suspend fun addHistory(context: Context,history: History) = AppDatabase.getInstance(context).getQRDao()?.insertAll(history)

    fun getLiveDataHistory(context: Context) = AppDatabase.getInstance(context).getQRDao().getLiveDataHistory()

    suspend fun deleteHistory(context: Context,history: History) = AppDatabase.getInstance(context).getQRDao()?.delete(history)

}
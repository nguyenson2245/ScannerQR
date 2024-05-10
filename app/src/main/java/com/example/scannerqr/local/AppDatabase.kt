package com.example.scannerqr.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quanlychitieu.local.dao.QRDao
import com.example.scannerqr.application.MainApp
import com.example.scannerqr.model.History

@Database(
    entities = [History::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getQRDao(): QRDao


    companion object {
        private var instance: AppDatabase? = null
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context, AppDatabase::class.java, "database-name"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}
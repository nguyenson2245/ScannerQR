package com.example.quanlychitieu.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.scannerqr.model.History

@Dao
interface QRDao {
    @Query("SELECT * FROM History")
    fun getAll(): List<History>

    @Insert
    suspend fun insertAll(vararg collect: History)

    @Delete
    suspend fun delete(collect: History)

    @Query("SELECT * FROM history")
    fun getLiveDataHistory(): LiveData<List<History>>
}
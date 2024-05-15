package com.example.scannerqr.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class History(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String = "",
    val icon: Int = 0,
    val date: String = "",
    val time: String = "",
): Serializable
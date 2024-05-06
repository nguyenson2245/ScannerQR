package com.example.scannerqr.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String = "",
    val icon: Int = 0,
    val date: String = "",
    val time: String = "",
    val type: String = "",
    val fragmentOpen: Class<*>
)
package com.example.scannerqr.model

data class Settings(
    val title: String = "",
    val icon: Int = 0,
    val description: String = "",
    val showButtonSwitch: Boolean = false,
    val switchEnabled: Boolean = false
)

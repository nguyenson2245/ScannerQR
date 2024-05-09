package com.example.scannerqr.model

data class Supported(
    var title: String = "",
    val icon: Int = 0,
    var content: String = "",
    val fragmentOpen: Class<*>? = null
)